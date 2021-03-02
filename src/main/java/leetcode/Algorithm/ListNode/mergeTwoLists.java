package leetcode.Algorithm.ListNode;

public class mergeTwoLists {

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        listNode.next= new ListNode(2);
        listNode.next.next= new ListNode(3);
        listNode.next.next.next= new ListNode(5);


        ListNode listNode2 = new ListNode(2);
        listNode2.next= new ListNode(4);
        listNode2.next.next= new ListNode(6);
        listNode2.next.next.next= new ListNode(8);

        System.out.println(mergeTwoLists(listNode,listNode2));
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        if(l1.val<l2.val){
          l1.next =  mergeTwoLists(l1.next,l2);
          return l1;
        }else{
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
}