package squery.app;

import java.util.concurrent.*;
import java.util.Collections;
import java.util.Arrays;

import squery.Squery;
import squery.Squery.$;

public class Main {
    public static void main(String[] args) {
        Squery squery;
        squery = $.equal("A", 1);
        System.out.println(squery.selection);
        squery = $.equal("B", 2);
        System.out.println(squery.selection);
        squery = $.equal("C", 3);
        System.out.println(squery.selection);

        // "A = ? and B = ? OR C = ?"
        // "(A = ?) and (B = ?) OR (C = ?)"
        squery = $.equal("A", 1).and().equal("B", 2).or().equal("C", 3);
        System.out.println(squery.selection);
        for (String p : squery.selectionArgs) {
            System.out.println(p);
        }

        // "(A = ? and B = ?) OR (C = ?)
        //
        squery = $.or(
                $.equal("A", 1).and().equal("B", 2),
                $.equal("C", 3)
                );
        System.out.println(squery.selection);
        for (String p : squery.selectionArgs) {
            System.out.println(p);
        }

        squery = $.and(
                $.equal("A", 1),
                $.equal("B", 2))
            .or().equal("C", 3);
        System.out.println(squery.selection);
        for (String p : squery.selectionArgs) {
            System.out.println(p);
        }

        squery = $.or(
                    $.and(
                        $.equal("A", 1),
                        $.equal("B", 2)
                    ),
                    $.equal("C", 3)
                );
        System.out.println(squery.selection);
        for (String p : squery.selectionArgs) {
            System.out.println(p);
        }
    }
}
