package com.story.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import android.database.sqlite.SQLiteQueryBuilder;

/**
 * The selection and selectionArgs helper, SQLiteQueryBuilder.appendWhere(where) whereClause alternative.
 *
 * Purpose
 *
 * To generate sql string of selection and selectionArgs from String[]/List<?> selectionArgs.
 *
 * For example:
 *
 *     (A = 1 and B = 2) OR (C = 3)
 *
 * String selection = "(A = ? and B = ?) OR (C = ?)";
 * selectionArgs[0] = "1";
 * selectionArgs[1] = "2";
 * selectionArgs[2] = "3";
 *
 * The structured expression:
 *
 * final Sqluery selection =
 *     $.or(
 *         $.and(
 *             $.in(Place.LOCATION, getIds(locations)),
 *             $.like(Place.NAME, pattern)
 *         ),
 *         $.notEqual(Place.NAME, pattern)
 *     );
 *
 * The cascaded expression:
 *
 * final Sqluery selection =
 *     new $(
 *         $.in(Place.LOCATION, getIds(locations))
 *         .and()
 *         .like(Place.NAME, pattern)
 *     ).or(
 *         $.notEqual(Place.NAME, pattern)
 *     );
 *
 * SqlectionBuilder
 * Sqluery
 * Selector
 * Sql
 * WhereClause
 * Where
 * Clause
 * Selection
 */

public class Sqluery {
    public static class $ extends Sqluery {
    }

    public String selection;
    public String[] selectionArgs;
    public String orderBy;

    public static final String NULL          = "NULL";
    public static final String IS            = "IS";
    public static final String IS_NOT        = "IS NOT";
    public static final String EQUAL         = "=";
    public static final String EQ            = EQUAL;
    public static final String NOT_EQUAL     = "!=";
    public static final String NEQ           = NOT_EQUAL;
    public static final String NQ            = NEQ;
    public static final String NE            = NQ;
    public static final String LESS_EQUAL    = "<=";
    public static final String LE            = LESS_EQUAL;
    public static final String LESS_THAN     = "<";
    public static final String LT            = LESS_THAN;
    public static final String GREATER_EQUAL = ">=";
    public static final String GE            = GREATER_EQUAL;
    public static final String GREATER_THAN  = ">";
    public static final String GT            = GREATER_THAN;
    public static final String AND           = "AND";
    public static final String OR            = "OR";
    public static final String NOT_IN        = "NOT IN";
    public static final String IN            = "IN";
    public static final String LIKE          = "LIKE";
    public static final String NOT_LIKE          = "NOT LIKE";

    public Sqluery() {
        selection = "";
        selectionArgs = new String[0];
        orderBy = "";
    }

    public Sqluery(Sqluery builder) {
        selection = builder.selection;
        selectionArgs = builder.selectionArgs;
        orderBy = builder.orderBy;
    }

    public static Sqluery isNull(String field) {
        return op(field, IS, NULL, false);
    }

    public static Sqluery isNotNull(String field) {
        return op(field, IS_NOT, NULL, false);
    }

    public static Sqluery equal(String field, Object selectionArg) {
        return op(field, EQ, selectionArg);
    }

    public static Sqluery notEqual(String field, Object selectionArg) {
        return op(field, NQ, selectionArg);
    }

    public static Sqluery lessEqual(String field, Object selectionArg) {
        return op(field, LE, selectionArg);
    }

    public static Sqluery lessThan(String field, Object selectionArg) {
        return op(field, LT, selectionArg);
    }

    public static Sqluery greaterEqual(String field, Object selectionArg) {
        return op(field, GE, selectionArg);
    }

    public static Sqluery greaterThan(String field, Object selectionArg) {
        return op(field, GT, selectionArg);
    }

    public static Sqluery notIn(String field, Object selectionArg) {
        return op(field, NOT_IN, selectionArg);
    }

    public static Sqluery in(String field, Object selectionArg) {
        return op(field, IN, selectionArg);
    }

    public static Sqluery equal(String field, List<?> selectionArgs) {
        return op(field, EQ, selectionArgs);
    }

    public static Sqluery notEqual(String field, List<?> selectionArgs) {
        return op(field, NQ, selectionArgs);
    }

    public static Sqluery lessEqual(String field, List<?> selectionArgs) {
        return op(field, LE, selectionArgs);
    }

    public static Sqluery lessThan(String field, List<?> selectionArgs) {
        return op(field, LT, selectionArgs);
    }

    public static Sqluery greaterEqual(String field, List<?> selectionArgs) {
        return op(field, GE, selectionArgs);
    }

    public static Sqluery greaterThan(String field, List<?> selectionArgs) {
        return op(field, GT, selectionArgs);
    }

    public static Sqluery notIn(String field, List<?> selectionArgs) {
        return op(field, NOT_IN, selectionArgs);
    }

    public static Sqluery in(String field, List<?> selectionArgs) {
        Sqluery out = op(field, IN, selectionArgs);

        StringBuilder builder = new StringBuilder("");
        if (selectionArgs.isEmpty()) {
            builder.append(field);
        } else {
            int i = 0;
            builder.append("CASE " + field + " ");
            for (Object o : selectionArgs) {
                builder.append("WHEN " + o.toString() + " THEN " + i + " ");
                i++;
            }
            builder.append("END");
        }

        out.orderBy = builder.toString();
        return out;
    }

    private static Sqluery op(String field, String op, Object selectionArg) {
        return op(field, op, selectionArg, true);
    }

    private static Sqluery op(String field, String op, Object selectionArg, boolean safe) {
        final Sqluery out = new Sqluery();

        if (safe) {
            out.selection = "(" + field + " " + op + " ?" + ")";
            out.selectionArgs = new String[1];
            out.selectionArgs[0] = String.valueOf(selectionArg);
        } else {
            out.selection = "(" + field + " " + op + " " + String.valueOf(selectionArg) + ")";
            out.selectionArgs = new String[0];
        }

        return out;
    }

    private static Sqluery op(String field, String op, List<?> selectionArgs) {
        return op(field, op, selectionArgs, false);
    }

    private static Sqluery op(String field, String op, List<?> selectionArgs,
            boolean safe) {
        final Sqluery out = new Sqluery();

        final int num = selectionArgs.size();

        final StringBuilder buf = new StringBuilder(field + " " + op + " (");
        if (safe)
            out.selectionArgs = new String[num];
        else
            out.selectionArgs = new String[0];

        for (int i = 0; i < num; i++) {
            final String s = String.valueOf(selectionArgs.get(i));

            if (safe) {
                buf.append("?");
                out.selectionArgs[i] = s;
            } else {
                buf.append(s);
            }

            if (i != num - 1)
                buf.append(",");
        }

        buf.append(")");

        out.selection = "(" + buf.toString() + ")";

        return out;
    }

    public static Sqluery[] like(String field, List<?> selectionArgs) {
        List<Sqluery> builders = new ArrayList<Sqluery>();
        for (Object object : selectionArgs) {
            builders.add(Sqluery.like(field, object));
        }
        return builders.toArray(new Sqluery[0]);
    }

    public static Sqluery like(String field, Object selectionArg) {
        return op(field, LIKE, "%" + selectionArg + "%");
    }

    public static Sqluery exactLike(String field, Object selectionArg) {
        return op(field, LIKE, selectionArg);
    }

    public static Sqluery notLike(String field, Object selectionArg) {
        return op(field, NOT_LIKE, "%" + selectionArg + "%");
    }

    public static Sqluery exactNotLike(String field, Object selectionArg) {
        return op(field, NOT_LIKE, selectionArg);
    }

    public static Sqluery or(Sqluery... builders) {
        return append(OR, builders);
    }

    public static Sqluery and(Sqluery... builders) {
        return append(AND, builders);
    }

    private static Sqluery append(String op, Sqluery... builders) {
        Sqluery builder = new Sqluery(builders[0]);
        for (int i = 1; i < builders.length; i++) {
            builder = append(builder, op, builders[i]);
        }

        builder.selection = "(" + builder.selection + ")";

        return builder;
    }

    private static Sqluery append(Sqluery builder, String op, Sqluery anotherbuilder) {
        builder.selection = builder.selection + " " + op + " " + anotherbuilder.selection;

        List<String> s = new ArrayList<String>();
        List<String> s1 = Arrays.asList(builder.selectionArgs);
        List<String> s2 = Arrays.asList(anotherbuilder.selectionArgs);

        s.addAll(s1);
        s.addAll(s2);
        builder.selectionArgs = s.toArray(new String[0]);

        return builder;
    }

    public String toSql() {
        String[] sels = selection.split("\\?");
        String ret = "";

        for (int i = 0; i < selectionArgs.length; i++) {
            ret += sels[i] + "'";
            ret += selectionArgs[i] + "'";
        }
        ret += sels[selectionArgs.length];

        return ret;
    }
}
