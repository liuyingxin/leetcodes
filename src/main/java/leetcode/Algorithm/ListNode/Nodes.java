package leetcode.Algorithm.ListNode;

public class Nodes {
    public Nodes next;
    public Character val;

    Nodes() {
    }

    public Nodes(Character val) {
        this.val = val;
    }

    Nodes(Character val, Nodes next) {
        this.val = val;
        this.next = next;
    }
    public boolean isNum(){
        int i = val - '0';
        return i >= 0 && i <= 9;
    }

    @Override
    public String toString() {
        Nodes head = this;
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val + " ");
            head = head.next;
        }
        return sb.toString();
    }
}

