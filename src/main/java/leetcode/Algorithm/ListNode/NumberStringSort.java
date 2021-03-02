package leetcode.Algorithm.ListNode;


public class NumberStringSort {
    public static Nodes sort(Nodes head) {
        Nodes numNode = null;
        Nodes numLastNode = null;
        Nodes next = head;
        Nodes charNode = null;
        Nodes newHead = null;
        if (head == null || head.next == null) {
            return head;
        }
        while (next != null) {
            //数字
            if (Character.isDigit(next.val)) {
                numNode.next = next;
                if(numLastNode==null){
                    numLastNode = next;
                }else{
                    numLastNode.next = next;
                }
            }else if (numLastNode == null){
                numLastNode = next;
            }else if (Character.isLowerCase(next.val)) {
                charNode =  next;
            }
            newHead = numNode;
            numNode.next = charNode;
        }

        return newHead;
    }
    public static Nodes sepLink(Nodes head){

        Nodes nHead = null;
        Nodes next = head;
        Nodes lastNum = null;
        Nodes firstUnnum = null;
        Nodes pre = null;
        while(next != null){
            if (Character.isDigit(next.val) && firstUnnum == null){
                lastNum = next;
            } else if (Character.isDigit(next.val)){
                pre.next = next.next;
                if (lastNum == null){
                    lastNum = next;
                }else{
                    lastNum.next = next;
                }
                next.next = firstUnnum;
                lastNum = next;
            } else if (firstUnnum == null){
                firstUnnum = next;
            }
            if (nHead == null){
                nHead = lastNum;
            }
            if (!Character.isDigit(next.val) || firstUnnum == null){
                pre = next;
            }

            next = pre.next;
        }

        return nHead == null ? head : nHead;
    }
    public static void main(String[] args) {
        Nodes Nodes = new Nodes('a');
        Nodes.next= new Nodes('2');
        Nodes.next.next= new Nodes('b');
        Nodes.next.next.next= new Nodes('c');


        System.out.println(Nodes);
        System.out.println("----");
        System.out.println( sort(Nodes));
        System.out.println( sepLink(Nodes));
    }
}
