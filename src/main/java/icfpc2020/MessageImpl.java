package icfpc2020;

public class MessageImpl implements Message {
    private final long[] message;
    private final int length;

    public MessageImpl(String s) {
        this(s.length());

        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '1') {
                setValue(i, 1);
            }
        }
    }

    public MessageImpl(int length) {
        this.length = length;
        message = new long[length / 64 + 1];
    }

    public void setValue(int position, int value) {
        assert value == 0 || value == 1: "Wrong value";
        message[position / 64] |= ((long) value) << position % 64;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public int getValueAt(int position) {
        final long l = message[position / 64] & 1L << position % 64;
        return l != 0L ? 1: 0;
    }

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder();
        for (int i = 0; i < length; i++) {
            b.append(getValueAt(i));
        }
        return b.toString();
    }

    public static void main(String[] args) {
        final String s = "110110000111011111100001001111110101000000";
        final Message m = new MessageImpl(s);
        System.out.println(s);
        System.out.println(m.toString());
        System.out.println(m.toString().equals(s));
    }
}
