package com.jeramtough.jtlog.util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018-09-08 10:11
 * by @author JeramTough
 */

public class IOUtil {
    public static final int EOF = -1;
    public static final char DIR_SEPARATOR_UNIX = '/';
    public static final char DIR_SEPARATOR_WINDOWS = '\\';
    public static final String LINE_SEPARATOR_UNIX = "\n";
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int SKIP_BUFFER_SIZE = 2048;
    private static char[] SKIP_CHAR_BUFFER;
    private static byte[] SKIP_BYTE_BUFFER;

    public IOUtil() {
    }

    public static void close(URLConnection conn) {
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection) conn).disconnect();
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void closeQuietly(Reader input) {
        closeQuietly((Closeable) input);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void closeQuietly(Writer output) {
        closeQuietly((Closeable) output);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void closeQuietly(InputStream input) {
        closeQuietly((Closeable) input);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void closeQuietly(OutputStream output) {
        closeQuietly((Closeable) output);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException var2) {
            ;
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void closeQuietly(Closeable... closeables) {
        if (closeables != null) {
            Closeable[] var1 = closeables;
            int var2 = closeables.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                Closeable closeable = var1[var3];
                closeQuietly(closeable);
            }

        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void closeQuietly(Socket sock) {
        if (sock != null) {
            try {
                sock.close();
            } catch (IOException var2) {
                ;
            }
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void closeQuietly(Selector selector) {
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException var2) {
                ;
            }
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void closeQuietly(ServerSocket sock) {
        if (sock != null) {
            try {
                sock.close();
            } catch (IOException var2) {
                ;
            }
        }

    }


    public static BufferedReader toBufferedReader(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    public static BufferedReader toBufferedReader(Reader reader, int size) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, size);
    }

    public static BufferedReader buffer(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    public static BufferedReader buffer(Reader reader, int size) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, size);
    }

    public static BufferedWriter buffer(Writer writer) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer);
    }

    public static BufferedWriter buffer(Writer writer, int size) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, size);
    }

    public static BufferedOutputStream buffer(OutputStream outputStream) {
        if (outputStream == null) {
            throw new NullPointerException();
        }
        else {
            return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream);
        }
    }

    public static BufferedOutputStream buffer(OutputStream outputStream, int size) {
        if (outputStream == null) {
            throw new NullPointerException();
        }
        else {
            return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, size);
        }
    }

    public static BufferedInputStream buffer(InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException();
        }
        else {
            return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
        }
    }

    public static BufferedInputStream buffer(InputStream inputStream, int size) {
        if (inputStream == null) {
            throw new NullPointerException();
        }
        else {
            return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, size);
        }
    }


    public static byte[] toByteArray(InputStream input, long size) throws IOException {
        if (size > 2147483647L) {
            throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + size);
        }
        else {
            return toByteArray(input, (int) size);
        }
    }

    public static byte[] toByteArray(InputStream input, int size) throws IOException {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + size);
        }
        else if (size == 0) {
            return new byte[0];
        }
        else {
            byte[] data = new byte[size];

            int offset;
            int read;
            for (offset = 0; offset < size && (read = input.read(data, offset, size - offset)) != -1; offset += read) {
                ;
            }

            if (offset != size) {
                throw new IOException("Unexpected read size. current: " + offset + ", expected: " + size);
            }
            else {
                return data;
            }
        }
    }




    /**
     * @deprecated
     */
    @Deprecated
    public static byte[] toByteArray(String input) throws IOException {
        return input.getBytes(Charset.defaultCharset());
    }




    /**
     * @deprecated
     */
    @Deprecated
    public static char[] toCharArray(InputStream is) throws IOException {
        return toCharArray(is, Charset.defaultCharset());
    }

    public static char[] toCharArray(InputStream is, Charset encoding) throws IOException {
        CharArrayWriter output = new CharArrayWriter();
        copy((InputStream) is, (Writer) output, (Charset) encoding);
        return output.toCharArray();
    }

    public static char[] toCharArray(InputStream is, String encoding) throws IOException {
        return toCharArray(is, Charsets.toCharset(encoding));
    }

    public static char[] toCharArray(Reader input) throws IOException {
        CharArrayWriter sw = new CharArrayWriter();
        copy((Reader) input, (Writer) sw);
        return sw.toCharArray();
    }





    /**
     * @deprecated
     */
    @Deprecated
    public static String toString(byte[] input) throws IOException {
        return new String(input, Charset.defaultCharset());
    }

    public static String toString(byte[] input, String encoding) throws IOException {
        return new String(input, Charsets.toCharset(encoding));
    }






    /**
     * @deprecated
     */
    @Deprecated
    public static List<String> readLines(InputStream input) throws IOException {
        return readLines(input, Charset.defaultCharset());
    }

    public static List<String> readLines(InputStream input, Charset encoding) throws IOException {
        InputStreamReader reader = new InputStreamReader(input, Charsets.toCharset(encoding));
        return readLines((Reader) reader);
    }



    public static List<String> readLines(Reader input) throws IOException {
        BufferedReader reader = toBufferedReader(input);
        List<String> list = new ArrayList();

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            list.add(line);
        }

        return list;
    }


    /**
     * @deprecated
     */
    @Deprecated
    public static InputStream toInputStream(CharSequence input) {
        return toInputStream(input, Charset.defaultCharset());
    }

    public static InputStream toInputStream(CharSequence input, Charset encoding) {
        return toInputStream(input.toString(), encoding);
    }

    public static InputStream toInputStream(CharSequence input, String encoding) throws IOException {
        return toInputStream(input, Charsets.toCharset(encoding));
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static InputStream toInputStream(String input) {
        return toInputStream(input, Charset.defaultCharset());
    }

    public static InputStream toInputStream(String input, Charset encoding) {
        return new ByteArrayInputStream(input.getBytes(Charsets.toCharset(encoding)));
    }

    public static InputStream toInputStream(String input, String encoding) throws IOException {
        byte[] bytes = input.getBytes(Charsets.toCharset(encoding));
        return new ByteArrayInputStream(bytes);
    }

    public static void write(byte[] data, OutputStream output) throws IOException {
        if (data != null) {
            output.write(data);
        }

    }

    public static void writeChunked(byte[] data, OutputStream output) throws IOException {
        if (data != null) {
            int bytes = data.length;

            int chunk;
            for (int offset = 0; bytes > 0; offset += chunk) {
                chunk = Math.min(bytes, 4096);
                output.write(data, offset, chunk);
                bytes -= chunk;
            }
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void write(byte[] data, Writer output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(byte[] data, Writer output, Charset encoding) throws IOException {
        if (data != null) {
            output.write(new String(data, Charsets.toCharset(encoding)));
        }

    }

    public static void write(byte[] data, Writer output, String encoding) throws IOException {
        write(data, output, Charsets.toCharset(encoding));
    }

    public static void write(char[] data, Writer output) throws IOException {
        if (data != null) {
            output.write(data);
        }

    }

    public static void writeChunked(char[] data, Writer output) throws IOException {
        if (data != null) {
            int bytes = data.length;

            int chunk;
            for (int offset = 0; bytes > 0; offset += chunk) {
                chunk = Math.min(bytes, 4096);
                output.write(data, offset, chunk);
                bytes -= chunk;
            }
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void write(char[] data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(char[] data, OutputStream output, Charset encoding) throws IOException {
        if (data != null) {
            output.write((new String(data)).getBytes(Charsets.toCharset(encoding)));
        }

    }

    public static void write(char[] data, OutputStream output, String encoding) throws IOException {
        write(data, output, Charsets.toCharset(encoding));
    }

    public static void write(CharSequence data, Writer output) throws IOException {
        if (data != null) {
            write(data.toString(), output);
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void write(CharSequence data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(CharSequence data, OutputStream output, Charset encoding) throws IOException {
        if (data != null) {
            write(data.toString(), output, encoding);
        }

    }

    public static void write(CharSequence data, OutputStream output, String encoding) throws IOException {
        write(data, output, Charsets.toCharset(encoding));
    }

    public static void write(String data, Writer output) throws IOException {
        if (data != null) {
            output.write(data);
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void write(String data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(String data, OutputStream output, Charset encoding) throws IOException {
        if (data != null) {
            output.write(data.getBytes(Charsets.toCharset(encoding)));
        }

    }

    public static void write(String data, OutputStream output, String encoding) throws IOException {
        write(data, output, Charsets.toCharset(encoding));
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void write(StringBuffer data, Writer output) throws IOException {
        if (data != null) {
            output.write(data.toString());
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void write(StringBuffer data, OutputStream output) throws IOException {
        write(data, output, (String) null);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void write(StringBuffer data, OutputStream output, String encoding) throws IOException {
        if (data != null) {
            output.write(data.toString().getBytes(Charsets.toCharset(encoding)));
        }

    }



    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        return count > 2147483647L ? -1 : (int) count;
    }

    public static long copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
        return copyLarge(input, output, new byte[bufferSize]);
    }

    public static long copyLarge(InputStream input, OutputStream output) throws IOException {
        return copy(input, output, 4096);
    }

    public static long copyLarge(InputStream input, OutputStream output, byte[] buffer) throws IOException {
        long count;
        int n;
        for (count = 0L; -1 != (n = input.read(buffer)); count += (long) n) {
            output.write(buffer, 0, n);
        }

        return count;
    }

    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length) throws IOException {
        return copyLarge(input, output, inputOffset, length, new byte[4096]);
    }

    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length, byte[] buffer) throws IOException {
        if (inputOffset > 0L) {
            skipFully(input, inputOffset);
        }

        if (length == 0L) {
            return 0L;
        }
        else {
            int bufferLength = buffer.length;
            int bytesToRead = bufferLength;
            if (length > 0L && length < (long) bufferLength) {
                bytesToRead = (int) length;
            }

            long totalRead = 0L;

            int read;
            while (bytesToRead > 0 && -1 != (read = input.read(buffer, 0, bytesToRead))) {
                output.write(buffer, 0, read);
                totalRead += (long) read;
                if (length > 0L) {
                    bytesToRead = (int) Math.min(length - totalRead, (long) bufferLength);
                }
            }

            return totalRead;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void copy(InputStream input, Writer output) throws IOException {
        copy(input, output, Charset.defaultCharset());
    }

    public static void copy(InputStream input, Writer output, Charset inputEncoding) throws IOException {
        InputStreamReader in = new InputStreamReader(input, Charsets.toCharset(inputEncoding));
        copy((Reader) in, (Writer) output);
    }

    public static void copy(InputStream input, Writer output, String inputEncoding) throws IOException {
        copy(input, output, Charsets.toCharset(inputEncoding));
    }

    public static int copy(Reader input, Writer output) throws IOException {
        long count = copyLarge(input, output);
        return count > 2147483647L ? -1 : (int) count;
    }

    public static long copyLarge(Reader input, Writer output) throws IOException {
        return copyLarge(input, output, new char[4096]);
    }

    public static long copyLarge(Reader input, Writer output, char[] buffer) throws IOException {
        long count;
        int n;
        for (count = 0L; -1 != (n = input.read(buffer)); count += (long) n) {
            output.write(buffer, 0, n);
        }

        return count;
    }

    public static long copyLarge(Reader input, Writer output, long inputOffset, long length) throws IOException {
        return copyLarge(input, output, inputOffset, length, new char[4096]);
    }

    public static long copyLarge(Reader input, Writer output, long inputOffset, long length, char[] buffer) throws IOException {
        if (inputOffset > 0L) {
            skipFully(input, inputOffset);
        }

        if (length == 0L) {
            return 0L;
        }
        else {
            int bytesToRead = buffer.length;
            if (length > 0L && length < (long) buffer.length) {
                bytesToRead = (int) length;
            }

            long totalRead = 0L;

            int read;
            while (bytesToRead > 0 && -1 != (read = input.read(buffer, 0, bytesToRead))) {
                output.write(buffer, 0, read);
                totalRead += (long) read;
                if (length > 0L) {
                    bytesToRead = (int) Math.min(length - totalRead, (long) buffer.length);
                }
            }

            return totalRead;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void copy(Reader input, OutputStream output) throws IOException {
        copy(input, output, Charset.defaultCharset());
    }

    public static void copy(Reader input, OutputStream output, Charset outputEncoding) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output, Charsets.toCharset(outputEncoding));
        copy((Reader) input, (Writer) out);
        out.flush();
    }

    public static void copy(Reader input, OutputStream output, String outputEncoding) throws IOException {
        copy(input, output, Charsets.toCharset(outputEncoding));
    }

    public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
        if (input1 == input2) {
            return true;
        }
        else {
            if (!(input1 instanceof BufferedInputStream)) {
                input1 = new BufferedInputStream((InputStream) input1);
            }

            if (!(input2 instanceof BufferedInputStream)) {
                input2 = new BufferedInputStream((InputStream) input2);
            }

            int ch2;
            for (int ch = ((InputStream) input1).read(); -1 != ch; ch = ((InputStream) input1).read()) {
                ch2 = ((InputStream) input2).read();
                if (ch != ch2) {
                    return false;
                }
            }

            ch2 = ((InputStream) input2).read();
            return ch2 == -1;
        }
    }


    public static boolean contentEqualsIgnoreEOL(Reader input1, Reader input2) throws IOException {
        if (input1 == input2) {
            return true;
        }
        else {
            BufferedReader br1 = toBufferedReader(input1);
            BufferedReader br2 = toBufferedReader(input2);
            String line1 = br1.readLine();

            String line2;
            for (line2 = br2.readLine(); line1 != null && line2 != null && line1.equals(line2); line2 = br2.readLine()) {
                line1 = br1.readLine();
            }

            return line1 == null ? line2 == null : line1.equals(line2);
        }
    }

    public static long skip(InputStream input, long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        else {
            if (SKIP_BYTE_BUFFER == null) {
                SKIP_BYTE_BUFFER = new byte[2048];
            }

            long remain;
            long n;
            for (remain = toSkip; remain > 0L; remain -= n) {
                n = (long) input.read(SKIP_BYTE_BUFFER, 0, (int) Math.min(remain, 2048L));
                if (n < 0L) {
                    break;
                }
            }

            return toSkip - remain;
        }
    }

    public static long skip(ReadableByteChannel input, long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        else {
            ByteBuffer skipByteBuffer = ByteBuffer.allocate((int) Math.min(toSkip, 2048L));

            long remain;
            int n;
            for (remain = toSkip; remain > 0L; remain -= (long) n) {
                skipByteBuffer.position(0);
                skipByteBuffer.limit((int) Math.min(remain, 2048L));
                n = input.read(skipByteBuffer);
                if (n == -1) {
                    break;
                }
            }

            return toSkip - remain;
        }
    }

    public static long skip(Reader input, long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        else {
            if (SKIP_CHAR_BUFFER == null) {
                SKIP_CHAR_BUFFER = new char[2048];
            }

            long remain;
            long n;
            for (remain = toSkip; remain > 0L; remain -= n) {
                n = (long) input.read(SKIP_CHAR_BUFFER, 0, (int) Math.min(remain, 2048L));
                if (n < 0L) {
                    break;
                }
            }

            return toSkip - remain;
        }
    }

    public static void skipFully(InputStream input, long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        else {
            long skipped = skip(input, toSkip);
            if (skipped != toSkip) {
                throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
            }
        }
    }

    public static void skipFully(ReadableByteChannel input, long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        else {
            long skipped = skip(input, toSkip);
            if (skipped != toSkip) {
                throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
            }
        }
    }

    public static void skipFully(Reader input, long toSkip) throws IOException {
        long skipped = skip(input, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Chars to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static int read(Reader input, char[] buffer, int offset, int length) throws IOException {
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        else {
            int remaining;
            int count;
            for (remaining = length; remaining > 0; remaining -= count) {
                int location = length - remaining;
                count = input.read(buffer, offset + location, remaining);
                if (-1 == count) {
                    break;
                }
            }

            return length - remaining;
        }
    }

    public static int read(Reader input, char[] buffer) throws IOException {
        return read((Reader) input, (char[]) buffer, 0, buffer.length);
    }

    public static int read(InputStream input, byte[] buffer, int offset, int length) throws IOException {
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        else {
            int remaining;
            int count;
            for (remaining = length; remaining > 0; remaining -= count) {
                int location = length - remaining;
                count = input.read(buffer, offset + location, remaining);
                if (-1 == count) {
                    break;
                }
            }

            return length - remaining;
        }
    }

    public static int read(InputStream input, byte[] buffer) throws IOException {
        return read((InputStream) input, (byte[]) buffer, 0, buffer.length);
    }

    public static int read(ReadableByteChannel input, ByteBuffer buffer) throws IOException {
        int length = buffer.remaining();

        while (buffer.remaining() > 0) {
            int count = input.read(buffer);
            if (-1 == count) {
                break;
            }
        }

        return length - buffer.remaining();
    }

    public static void readFully(Reader input, char[] buffer, int offset, int length) throws IOException {
        int actual = read(input, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    public static void readFully(Reader input, char[] buffer) throws IOException {
        readFully((Reader) input, (char[]) buffer, 0, buffer.length);
    }

    public static void readFully(InputStream input, byte[] buffer, int offset, int length) throws IOException {
        int actual = read(input, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    public static void readFully(InputStream input, byte[] buffer) throws IOException {
        readFully((InputStream) input, (byte[]) buffer, 0, buffer.length);
    }

    public static byte[] readFully(InputStream input, int length) throws IOException {
        byte[] buffer = new byte[length];
        readFully((InputStream) input, (byte[]) buffer, 0, buffer.length);
        return buffer;
    }

    public static void readFully(ReadableByteChannel input, ByteBuffer buffer) throws IOException {
        int expected = buffer.remaining();
        int actual = read(input, buffer);
        if (actual != expected) {
            throw new EOFException("Length to read: " + expected + " actual: " + actual);
        }
    }


}

