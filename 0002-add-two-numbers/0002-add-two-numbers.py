# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: Optional[ListNode]
        :type l2: Optional[ListNode]
        :rtype: Optional[ListNode]
        """
        dummy = ListNode(0)
        current = dummy
        carry = 0
        
        # Loop until both lists are empty and there's no carry left
        while l1 or l2 or carry:
            # Get values from the current nodes, or 0 if the list is finished
            val1 = l1.val if l1 else 0
            val2 = l2.val if l2 else 0
            
            # Calculate the sum and the new carry
            total = val1 + val2 + carry
            carry = total // 10
            
            # Create a new node with the digit part of the sum
            current.next = ListNode(total % 10)
            
            # Move the pointers forward
            current = current.next
            if l1: l1 = l1.next
            if l2: l2 = l2.next
            
        return dummy.next