package com.lxf.stock.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NioService {
    private Selector selector;

    public void init() throws IOException {

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(8000));
        socketChannel.configureBlocking(false);
        this.selector = Selector.open();
        socketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
    }

    public void lister() throws IOException {
        while (true) {
            this.selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handler(selectionKey);
            }
        }
    }

    public void handler(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            handlerAccepta(selectionKey);
        } else if (selectionKey.isReadable()) {
            handlerReadable(selectionKey);
        }

    }

    public void handlerAccepta(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel accept = channel.accept();
        accept.configureBlocking(false);
        accept.register(this.selector, SelectionKey.OP_READ);
    }

    public void handlerReadable(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        channel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = channel.read(byteBuffer);
        if(read >0 ){
            System.out.println(new String(byteBuffer.array(),"GBK"));
            byteBuffer.clear();
            byte[] bytes = new String("收到你的消息了3q".getBytes(),"GBK").getBytes();
            byteBuffer.put(bytes);
            channel.write(byteBuffer);
        }
    }

    public static void main(String[] args) throws IOException {
        NioService nioService = new NioService();
        nioService.init();
        nioService.lister();
    }
}
