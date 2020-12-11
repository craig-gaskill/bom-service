package com.cagst.bom.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * Provides common utilities that can be used across Strings.
 *
 * @author Craig Gaskill
 */
public abstract class CommonStringUtils extends StringUtils {
    private static final Pattern REPLACE_PATTERN = Pattern.compile("[^\\p{L} 0-9]");

    /**
     * Normalizes the specified name into a valid key.  Will perform the following operations:
     * <ul>
     *     <li>Strip Accents</li>
     *     <li>Remove all characters that are not in "a-zA-A0-9 "</li>
     *     <li>Normalize Space</li>
     *     <li>Replace Space with '_'</li>
     *     <li>Uppercase</li>
     * </ul>
     *
     * @param input
     *      The {@link String} to be normalized.
     *
     * @return A {@link String} that is the normalized key representation of the specified string.
     */
    @NonNull
    public static String normalizeToKey(@NonNull String input) {
        Assert.hasText(input, "Argument [input] cannot be null or empty.");

        var partialKey = normalizeSpace(stripAccents(input));

        Matcher matcher = REPLACE_PATTERN.matcher(partialKey);
        partialKey = matcher.replaceAll("");

        return upperCase(replaceChars(partialKey, ' ', '_'));
    }
}
