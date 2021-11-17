# Collections

## Requirements:

### ListCapacity. (easy)
- Create class ensureCapacity
- Create algorithm that read each line of the file (resources/testfile.txt)
- Add each line into ArrayList
- Ensure capacity for ArrayList
- delete half of array
- shrink arraylist

### List modification (easy)
- Create class ListModification
- Create ArrayList with 0,0,0,0,1,2,3,4,5,6,7,8,9
- delete 0 from list using while.
- sort list from even to odd. There should be even numbers at the beginning of the list and odd numbers at the end. Should be 8, 6, 4, 2, 1, 3, 5, 7, 9
- delete every second digit. Should be 8, 4, 1, 5, 9
- delete 1 and 5 using iterator. Should be 8,4,9

### List duplicate (normal)
- Create class ListDuplicate
- Create ArrayList with "1","1","2","2","3","4","5","6","7","8","9","10","10","10","10"
- Create method findAllDuplicate and find all duplicate
  - Step 1. Use HashSet
  - Step 2. Use HashMap
  - Step 3. Use sort list
  - Step 4. Use Stream API
- Create method findAllUnique and find all unique
- create method countWordOccurrence and count How many times does the word in list occur

### List. Find the sum of two array elements
- Create class ListFindSumOfTwoElements
- We have an array with (1,2,3,4) elements, and we need method to check are there two elements whose sum can be equal to 6 or not. For this example 2 + 4 = 6.
  - Create method checkByTraversingArrayInArray. Use a square complexity algorithm to check the elements. (n2)
  - Create method checkByHashMap. Use hashSet to check it. (n)

### Map modification (easy)
- Create class map modification
- Create map with initial capacity(8)
- Add in map 8 elements with unique hash code and check current capacity (use reflection)
- Create new map with initial capacity(8)
- Add in map 20 elements with similar hash code, but not equals with each other, and check current capacity.

