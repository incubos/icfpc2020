package icfpc2020;

public interface Message {
    int getLength();
    void setValue(int position, int value);
    int getValueAt(int position);
}


