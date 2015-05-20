/*
 * Copyright 2015 8tory, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package squery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

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
 * final Squery selection =
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
 * final Squery selection =
 *     new $(
 *         $.in(Place.LOCATION, getIds(locations))
 *         .and()
 *         .like(Place.NAME, pattern)
 *     ).or(
 *         $.notEqual(Place.NAME, pattern)
 *     );
 *
 * SqlectionBuilder
 * Squery
 * Squery
 * Selector
 * Sql
 * WhereClause
 * Where
 * Clause
 * Selection
 * Builder.eq() // builde()
 * $.eq(a, 1).and().eq(b, 2)
 *
 * vs.
 *
 * $and(
 *      $.eq(a, 1),
 *      $.eq(b, 2)
 *  );
 *
 * vs.
 *
 * $and().begin()
 *      $.eq(a, 1),
 *      $.eq(b, 2)
 *  .end();
 */

public class Squery {

    public static class $ extends Builder {
    }

    public String selection;
    public String[] selectionArgs;
    public String orderBy;

    public Squery() {
        selection = "";
        selectionArgs = new String[0];
        orderBy = "";
    }

    public Squery(Squery squery) {
        this();
        append(squery);
    }

    public Squery(Builder builder) {
        this();
        append(builder);
    }

    public Squery append(Squery squery) {
        selection += squery.selection;

        List<String> s = new ArrayList<String>();
        List<String> s1 = Arrays.asList(selectionArgs);
        List<String> s2 = Arrays.asList(squery.selectionArgs);
        //List<String> s1 = (selectionArgs != null) ? Arrays.asList(selectionArgs) : Collections.emptyList();
        //List<String> s2 = (squery.selectionArgs != null) ? Arrays.asList(squery.selectionArgs) : Collections.emptyList();

        s.addAll(s1);
        s.addAll(s2);

        selectionArgs = s.toArray(new String[0]);

        return this;
    }

    public Squery append(Builder builder) {
        selection += builder.selection;

        List<String> s = new ArrayList<String>();
        List<String> s1 = Arrays.asList(selectionArgs);
        List<String> s2 = Arrays.asList(builder.selectionArgs);
        //List<String> s1 = (selectionArgs != null) ? Arrays.asList(selectionArgs) : Collections.emptyList();
        //List<String> s2 = (builder.selectionArgs != null) ? Arrays.asList(builder.selectionArgs) : Collections.emptyList();

        s.addAll(s1);
        s.addAll(s2);

        selectionArgs = s.toArray(new String[0]);

        return this;
    }

    // TODO
    public Squery[] append(Squery[] squeries) {
        /*
        for (Squery squery : squeries) {
            selection += squery.selection;

            List<String> s = new ArrayList<String>();
            List<String> s1 = Arrays.asList(selectionArgs);
            List<String> s2 = Arrays.asList(squery.selectionArgs);

            s.addAll(s1);
            s.addAll(s2);

            selectionArgs = s.toArray(new String[0]);
        }
        */

        return squeries;
    }

    public Squery isNull(String field) {
        return append(Builder.isNull(field));
    }

    public Squery isNotNull(String field) {
        return append(Builder.isNotNull(field));
    }

    public Squery equal(String field, Object selectionArg) {
        return append(Builder.equal(field, selectionArg));
    }

    public Squery notEqual(String field, Object selectionArg) {
        return append(Builder.notEqual(field, selectionArg));
    }

    public Squery lessEqual(String field, Object selectionArg) {
        return append(Builder.lessEqual(field, selectionArg));
    }

    public Squery lessThan(String field, Object selectionArg) {
        return append(Builder.lessThan(field, selectionArg));
    }

    public Squery greaterEqual(String field, Object selectionArg) {
        return append(Builder.greaterEqual(field, selectionArg));
    }

    public Squery greaterThan(String field, Object selectionArg) {
        return append(Builder.greaterThan(field, selectionArg));
    }

    public Squery notIn(String field, Object selectionArg) {
        return append(Builder.notIn(field, selectionArg));
    }

    public Squery in(String field, Object selectionArg) {
        return append(Builder.in(field, selectionArg));
    }

    public Squery equal(String field, List<?> selectionArgs) {
        return append(Builder.equal(field, selectionArgs));
    }

    public Squery notEqual(String field, List<?> selectionArgs) {
        return append(Builder.notEqual(field, selectionArgs));
    }

    public Squery lessEqual(String field, List<?> selectionArgs) {
        return append(Builder.lessEqual(field, selectionArgs));
    }

    public Squery lessThan(String field, List<?> selectionArgs) {
        return append(Builder.lessThan(field, selectionArgs));
    }

    public Squery greaterEqual(String field, List<?> selectionArgs) {
        return append(Builder.greaterEqual(field, selectionArgs));
    }

    public Squery greaterThan(String field, List<?> selectionArgs) {
        return append(Builder.greaterThan(field, selectionArgs));
    }

    public Squery notIn(String field, List<?> selectionArgs) {
        return append(Builder.notIn(field, selectionArgs));
    }

    public Squery in(String field, List<?> selectionArgs) {
        return append(Builder.in(field, selectionArgs));
    }

    public Squery[] like(String field, List<?> selectionArgs) {
        return append(Builder.like(field, selectionArgs));
    }

    public Squery like(String field, Object selectionArg) {
        return append(Builder.like(field, selectionArg));
    }

    public Squery exactLike(String field, Object selectionArg) {
        return append(Builder.exactLike(field, selectionArg));
    }

    public Squery notLike(String field, Object selectionArg) {
        return append(Builder.notLike(field, selectionArg));
    }

    public Squery exactNotLike(String field, Object selectionArg) {
        return append(Builder.exactNotLike(field, selectionArg));
    }

    public Squery and() {
        selection += " " + AND + " ";
        return this;
    }

    public Squery or() {
        selection += " " + OR + " ";
        return this;
    }

    // TODO? public static Squery and(Builder... builders)

    @Override
    public String toString() {
        return toSql();
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
    public static final String NOT_LIKE      = "NOT LIKE";

    public static class Builder {
        public String selection;
        public String[] selectionArgs;
        public String orderBy;

        public Builder() {
            selection = "";
            selectionArgs = new String[0];
            orderBy = "";
        }

        public Builder(Builder builder) {
            this();
            selection = builder.selection;
            selectionArgs = builder.selectionArgs;
            orderBy = builder.orderBy;
        }

        public Builder(Squery squery) {
            this();
            selection = squery.selection;
            selectionArgs = squery.selectionArgs;
            orderBy = squery.orderBy;
        }

        public static Squery isNull(String field) {
            return op(field, IS, NULL, false);
        }

        public static Squery isNotNull(String field) {
            return op(field, IS_NOT, NULL, false);
        }

        public static Squery equal(String field, Object selectionArg) {
            return op(field, EQ, selectionArg);
        }

        public static Squery notEqual(String field, Object selectionArg) {
            return op(field, NQ, selectionArg);
        }

        public static Squery lessEqual(String field, Object selectionArg) {
            return op(field, LE, selectionArg);
        }

        public static Squery lessThan(String field, Object selectionArg) {
            return op(field, LT, selectionArg);
        }

        public static Squery greaterEqual(String field, Object selectionArg) {
            return op(field, GE, selectionArg);
        }

        public static Squery greaterThan(String field, Object selectionArg) {
            return op(field, GT, selectionArg);
        }

        public static Squery notIn(String field, Object selectionArg) {
            return op(field, NOT_IN, selectionArg);
        }

        public static Squery in(String field, Object selectionArg) {
            return op(field, IN, selectionArg);
        }

        public static Squery equal(String field, List<?> selectionArgs) {
            return op(field, EQ, selectionArgs);
        }

        public static Squery notEqual(String field, List<?> selectionArgs) {
            return op(field, NQ, selectionArgs);
        }

        public static Squery lessEqual(String field, List<?> selectionArgs) {
            return op(field, LE, selectionArgs);
        }

        public static Squery lessThan(String field, List<?> selectionArgs) {
            return op(field, LT, selectionArgs);
        }

        public static Squery greaterEqual(String field, List<?> selectionArgs) {
            return op(field, GE, selectionArgs);
        }

        public static Squery greaterThan(String field, List<?> selectionArgs) {
            return op(field, GT, selectionArgs);
        }

        public static Squery notIn(String field, List<?> selectionArgs) {
            return op(field, NOT_IN, selectionArgs);
        }

        public static Squery in(String field, List<?> selectionArgs) {
            Squery out = op(field, IN, selectionArgs);

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

        private static Squery op(String field, String op, Object selectionArg) {
            return op(field, op, selectionArg, true);
        }

        private static Squery op(String field, String op, Object selectionArg, boolean safe) {
            final Squery out = new Squery();

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

        private static Squery op(String field, String op, List<?> selectionArgs) {
            return op(field, op, selectionArgs, false);
        }

        private static Squery op(String field, String op, List<?> selectionArgs,
                boolean safe) {
            final Squery out = new Squery();

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

        public static Squery[] like(String field, List<?> selectionArgs) {
            List<Squery> builders = new ArrayList<Squery>();
            for (Object object : selectionArgs) {
                builders.add(Builder.like(field, object));
            }
            return builders.toArray(new Squery[0]);
        }

        public static Squery like(String field, Object selectionArg) {
            return op(field, LIKE, "%" + selectionArg + "%");
        }

        public static Squery exactLike(String field, Object selectionArg) {
            return op(field, LIKE, selectionArg);
        }

        public static Squery notLike(String field, Object selectionArg) {
            return op(field, NOT_LIKE, "%" + selectionArg + "%");
        }

        public static Squery exactNotLike(String field, Object selectionArg) {
            return op(field, NOT_LIKE, selectionArg);
        }

        public static Squery or(Squery... builders) {
            return append(OR, builders);
        }

        public static Squery and(Squery... builders) {
            return append(AND, builders);
        }

        private static Squery append(String op, Squery... builders) {
            Squery builder = new Squery(builders[0]);
            for (int i = 1; i < builders.length; i++) {
                builder = append(builder, op, builders[i]);
            }

            builder.selection = "(" + builder.selection + ")"; // unnecessary?

            return builder;
        }

        private static Squery append(Squery builder, String op, Squery anotherbuilder) {
            builder.selection = "(" + builder.selection + ") " + op + " (" + anotherbuilder.selection + ")";

            List<String> s = new ArrayList<String>();
            List<String> s1 = Arrays.asList(builder.selectionArgs);
            List<String> s2 = Arrays.asList(anotherbuilder.selectionArgs);

            s.addAll(s1);
            s.addAll(s2);
            builder.selectionArgs = s.toArray(new String[0]);

            return builder;
        }

        @Override
        public String toString() {
            return toSql();
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
}
