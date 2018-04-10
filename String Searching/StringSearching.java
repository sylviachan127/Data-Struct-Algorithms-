import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuen Han Chan
 */
public class StringSearching implements StringSearchingInterface {

    @Override
    public List<Integer> boyerMoore(CharSequence pattern, CharSequence text) {
        if (pattern == null || pattern.length() == 0 || text == null) {
            throw new IllegalArgumentException();
        }
        if (text.length() == 0 || pattern.length() > text.length()) {
            List<Integer> emptyList = new ArrayList<Integer>();
            return emptyList;
        }
        int[] lastTable = buildLastTable(pattern);
        List<Integer> match = new ArrayList<Integer>();
        int currentCount = pattern.length() - 1;
        boolean cont = true;
        int innerCount;
        boolean complete = false;
        while (!complete) {
            innerCount = currentCount;
            for (int a = pattern.length() - 1; (cont && a >= 0); a--) {
                char currentPatternChar = pattern.charAt(a);
                char currentTextChar = text.charAt(innerCount);
                if (!(currentPatternChar == (currentTextChar))) {
                    if (lastTable[currentTextChar] != -1) {
                        int lastOccur = lastTable[currentTextChar];
                        int newCount = innerCount + pattern.length()
                                - lastOccur - 1;
                        // Just in case we are backtracking//
                        if (newCount == currentCount
                            || newCount < currentCount) {
                            currentCount++;
                        } else {
                            currentCount = newCount;
                        }
                    } else {
                        currentCount = innerCount + pattern.length();
                    }

                    if (currentCount > text.length() - 1) {
                        complete = true;
                    }
                    innerCount = currentCount;
                    cont = false;

                }
                if (cont) {
                    innerCount--;
                    if (a == 0) {
                        match.add(currentCount - pattern.length() + 1);
                        // What to do after find a match
                        currentCount++;
                    }
                }
            }
            cont = true;
            if (currentCount > text.length() - 1) {
                complete = true;
            }
        }
        return match;
    }

    @Override
    public int[] buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException();
        }
        int[] lastTable = new int[Character.MAX_VALUE + 1];
        for (int i = 0; i < lastTable.length; i++) {
            lastTable[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            lastTable[pattern.charAt(i)] = i;
        }
        return lastTable;
    }

    @Override
    public int generateHash(CharSequence current, int length) {
        if (current == null || (length < 0) || (length > current.length())) {
            throw new IllegalArgumentException();
        }
        int hash = 0;
        int base = BASE;
        for (int i = 0; i < length; i++) {
            int exponent = length - 1 - i;
            if (exponent == 0) {
                base = 1;
            } else if (exponent == 1) {
                base = BASE;
            } else {
                for (int j = 0; j < (exponent - 1); j++) {
                    base *= BASE;
                }
            }
            hash += (current.charAt(i) * base);
            base = BASE;
        }
        return hash;
    }

    @Override
    public int updateHash(int oldHash, int length, char oldChar, char newChar) {
        int base = BASE;

        int exponent = length - 1;
        if (exponent == 0) {
            base = 1;
        } else if (exponent == 1) {
            base = BASE;
        } else {
            for (int j = 0; j < (exponent - 1); j++) {
                base *= BASE;
            }
        }
        oldHash = (oldHash - (oldChar * base)) * BASE;
        return oldHash + newChar;
    }

    @Override
    public List<Integer> rabinKarp(CharSequence pattern, CharSequence text) {
        if (pattern == null || (pattern.length() == 0) || (text == null)) {
            throw new IllegalArgumentException();
        }
        boolean cont = true;
        List<Integer> matches = new ArrayList<>();
        if (text.length() == 0 || pattern.length() > text.length()) {
            return matches;
        }
        boolean finish = false;
        int patternHash = generateHash(pattern, pattern.length());
        int textHash = generateHash(text, pattern.length());
        if (patternHash == textHash) {
            for (int i = pattern.length() - 1; cont && (i >= 0); i--) {
                if (!(pattern.charAt(i) == text.charAt(i))) {
                    cont = false;
                }
                if (cont && i == 0) {
                    matches.add(0);
                }
            }
        }
        int beginIndex = 1;

        if ((text.length() - beginIndex) < (pattern.length())) {
            finish = true;
        }
        cont = true;
        while (!finish) {
            textHash = updateHash(textHash, pattern.length(),
                    text.charAt(beginIndex - 1),
                    text.charAt(beginIndex + pattern.length() - 1));
            if (patternHash == textHash) {
                for (int a = pattern.length() - 1; (cont && (a >= 0)); a--) {
                    if (!(pattern.charAt(a) == text.charAt(beginIndex + a))) {
                        cont = false;
                    }
                    if (cont && a == 0) {
                        matches.add(beginIndex);
                    }
                }
            }
            beginIndex++;
            cont = true;
            if ((text.length() - beginIndex) < (pattern.length())) {
                finish = true;
            }
        }

        return matches;
    }
}
