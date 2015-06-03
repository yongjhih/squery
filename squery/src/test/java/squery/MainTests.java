package squery;

import java.util.Collections;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import squery.Squery;
import squery.Squery.$;

import org.junit.Test;

public class MainTests {

    @Test
    public void testEqual() {
        Squery squery;

        squery = $.equal("A", 1);

        assertEquals("(A = '1')", squery.toString());
        assertEquals("(A = ?)", squery.selection);

        String[] expectedSelectionArgs = new String[] { "1" };
        assertArrayEquals(expectedSelectionArgs, squery.selectionArgs);
    }

    @Test
    public void testCascadedAndOr() {
        Squery squery;

        squery = $.equal("A", 1).and().equal("B", 2).or().equal("C", 3);

        // (A = '1') AND (B = '2') OR (C = '3')
        System.out.println(squery.toString());
        // (A = ?) AND (B = ?) OR (C = ?), [1, 2, 3]
        System.out.println(squery.selection + ", " + Arrays.deepToString(squery.selectionArgs));

        assertEquals("(A = '1') AND (B = '2') OR (C = '3')", squery.toString());
        assertEquals("(A = ?) AND (B = ?) OR (C = ?)", squery.selection);

        String[] expectedSelectionArgs = new String[] { "1", "2", "3" };
        assertArrayEquals(expectedSelectionArgs, squery.selectionArgs);
    }

    @Test
    public void testCombineCascadedStructuredAndOr() {
        Squery squery;
        String[] expectedSelectionArgs;

        squery = $.or(
                    $.equal("A", 1).and().equal("B", 2),
                    $.equal("C", 3)
                );

        System.out.println(squery.toString());
        // (((A = '1') AND (B = '2')) OR ((C = '3')))
        System.out.println(squery.selection + ", " + Arrays.deepToString(squery.selectionArgs));
        // (((A = ?) AND (B = ?)) OR ((C = ?))), [1, 2, 3]

        assertEquals("(((A = '1') AND (B = '2')) OR ((C = '3')))", squery.toString());
        assertEquals("(((A = ?) AND (B = ?)) OR ((C = ?)))", squery.selection);

        expectedSelectionArgs = new String[] { "1", "2", "3" };
        assertArrayEquals(expectedSelectionArgs, squery.selectionArgs);

        squery = $.and(
                $.equal("A", 1),
                $.equal("B", 2)
            )
            .or().equal("C", 3);

        System.out.println(squery.toString());
        // (((A = '1')) AND ((B = '2'))) OR (C = '3')
        System.out.println(squery.selection + ", " + Arrays.deepToString(squery.selectionArgs));
        // (((A = ?)) AND ((B = ?))) OR (C = ?), [1, 2, 3]

        assertEquals("(((A = '1')) AND ((B = '2'))) OR (C = '3')", squery.toString());
        assertEquals("(((A = ?)) AND ((B = ?))) OR (C = ?)", squery.selection);

        expectedSelectionArgs = new String[] { "1", "2", "3" };
        assertArrayEquals(expectedSelectionArgs, squery.selectionArgs);

        squery = $.or(
                    $.and(
                        $.equal("A", 1),
                        $.equal("B", 2)
                    ),
                    $.equal("C", 3)
                );

        System.out.println(squery.toString());
        // (((((A = '1')) AND ((B = '2')))) OR ((C = '3')))
        System.out.println(squery.selection + ", " + Arrays.deepToString(squery.selectionArgs));
        // (((((A = ?)) AND ((B = ?)))) OR ((C = ?))), [1, 2, 3]

        assertEquals("(((((A = '1')) AND ((B = '2')))) OR ((C = '3')))", squery.toString());
        assertEquals("(((((A = ?)) AND ((B = ?)))) OR ((C = ?)))", squery.selection);

        expectedSelectionArgs = new String[] { "1", "2", "3" };
        assertArrayEquals(expectedSelectionArgs, squery.selectionArgs);
    }

    @Test
    public void testBrace() {
        Squery squery;
        String[] expectedSelectionArgs;

        squery = $.of(
                    $.equal("A", 1).and().equal("B", 2)
                ).or().equal("C", 3);

        System.out.println(squery.toString());
        System.out.println(squery.selection + ", " + Arrays.deepToString(squery.selectionArgs));

        assertEquals("((A = '1') AND (B = '2')) OR (C = '3')", squery.toString());
        assertEquals("((A = ?) AND (B = ?)) OR (C = ?)", squery.selection);
        expectedSelectionArgs = new String[] { "1", "2", "3" };
        assertArrayEquals(expectedSelectionArgs, squery.selectionArgs);
    }

    @Test
    public void testBeginEnd() {
        Squery squery;
        String[] expectedSelectionArgs;

        squery = $.begin()
                    .equal("A", 1).and().equal("B", 2)
                .end().or().equal("C", 3);

        System.out.println(squery.toString());
        System.out.println(squery.selection() + ", " + Arrays.deepToString(squery.selectionArgs()));

        assertEquals("((A = '1') AND (B = '2')) OR (C = '3')", squery.toString());
        assertEquals("((A = ?) AND (B = ?)) OR (C = ?)", squery.selection);
        expectedSelectionArgs = new String[] { "1", "2", "3" };
        assertArrayEquals(expectedSelectionArgs, squery.selectionArgs);
    }

    @Test
    public void testIn() {
    /*
       (Id IN (1,2,3))
       (Id IN (1,2,3)), []
       (Id IN ('1','2','3'))
       (Id IN (?,?,?)), [1, 2, 3]

        squery = $.in("Id", Arrays.asList("1", "2", "3"));
        System.out.println(squery.toString());
        System.out.println(squery.selection + ", " + Arrays.deepToString(squery.selectionArgs));

        squery = $.in("Id", Arrays.asList(1, 2, 3), true);
        System.out.println(squery.toString());
        System.out.println(squery.selection + ", " + Arrays.deepToString(squery.selectionArgs));
    */
    }
}
