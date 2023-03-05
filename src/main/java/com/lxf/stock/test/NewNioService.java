package com.lxf.stock.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NewNioService {

    private Selector selector;

    public static void main(String[] args) throws IOException {
        NewNioService newNioService = new NewNioService();
        newNioService.init();
        newNioService.lister();
    }

    public void init() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9000));
        serverSocketChannel.configureBlocking(false);
        this.selector = Selector.open();
        serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT,this.selector);

    }

    public void lister() throws IOException {
        while (true){
            this.selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if(selectionKey.isAcceptable()){
                    handlerAccept(selectionKey);
                }else if(selectionKey.isReadable()){
                    handlerRead(selectionKey);
                }
            }
        }

    }

    private void handlerRead(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = channel.read(byteBuffer);
        if (read >0) {
            InetSocketAddress remoteAddress = (InetSocketAddress) channel.getRemoteAddress();
            String hostName = remoteAddress.getHostName();
            int port = remoteAddress.getPort();
            String hostAddress = remoteAddress.getAddress().getHostAddress();
            String s=hostName+"("+hostAddress+"):"+port;
            System.out.println(s+":"+new String(byteBuffer.array(),"GBK"));
            byteBuffer.clear();
            byteBuffer.put("ni hao shou dao xiao xi le".getBytes());
            channel.write(byteBuffer);
        }else {
            System.out.println("guang bi le ");
            selectionKey.cancel();
        }
    }

    private void handlerAccept(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel accept = channel.accept();
        accept.configureBlocking(false);
        accept.register(this.selector,SelectionKey.OP_READ);
    }
}
