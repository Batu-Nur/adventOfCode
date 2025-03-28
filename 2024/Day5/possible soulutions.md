# Solution suggestions

Of course using concurrecy and paralellism would improve all of these solutions. Maybe try "Bag of tasks" in the future for this, task being checking order of pages.
IO can most likely be parellelized too however need to read and process all rules before can start with pages.

## 1
Rules are represented as a key value map where the key is page number p and the values are the set of successors that page p has. Ex: \{61: \{13,29,53\}\} meaning 13,29 and 53 all come after 61 at some point if both 61 and any of the numbers in that set exist in same page update.

Pages updates represented as key value map where the key is page number p and the value is its index in the update.

To check for correct ordering, iterate through page num p in the page updates. For each p, check if its successors exist in the page update, and for existing ones check for ordering.  

Complexity: 
Assume page update of length n and rules with each having length m. Then in worst case scenario we would expect the runtime to be proportional to n*m, 
as we iterate through successors of p for each p to check its existence in the page updates.
So we got a O(n*m) complexity.  

## 2 
Use data representation as 1.
Considering page updates are much shorter than rules in the input, iterating through the other elements of the list checking if those are in the successors set can be better. If we use a hashmap/HashSet, the rule lookups should take O(1) time. Now we check n-1 elems at each iteration, O(n^2) complexity


This is much better than first solution if m > n