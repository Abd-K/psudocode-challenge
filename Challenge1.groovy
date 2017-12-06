//This function takes a array of integers, and returns how many of the numbers are
//odd and how many are even.
def countOddAndEvenNumbers(int[] input) {
    int odds = 0;
    int evens = 0;
    for(int i = 0; i < input.length; i++) {  
        if(i % 2 == 0) {
            evens++;
        } else {
            odds++;
        }
    }
    return [odds, evens];
}