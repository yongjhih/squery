[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-8Query-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1846)

[![8Query](art/8Query.png)](art/8Query.png)

# 8Query

Easy to build selection and selectionArgs for sql.

A `SQLiteQueryBuilder.appendWhere(where)` of whereClause alternative.

## Usage

For example:

```java
A = 1 AND B = 2 OR C = 3
```

Before:

```java
String selection = "A = ? and B = ? OR C = ?";
String[] selectionArgs = new String[] { "1", "2", "3" };

getContentResolver().query(uri, projections, selection, selectionArgs, sortOrder);
```

After:

```java
Squery squery = $.equal("A", 1).and().equal("B", 2).or().equal("C", 3);

getContentResolver().query(uri, projections, squery.selection, squery.selectionArgs, sortOrder);
// whereClause
// String sql = squery.toString(); // (A = '1') AND (B = '2') OR (C = '3')
```

Orderby:

```java
squery = squery.desc("D").asc("E").limit(10);

getContentResolver().query(uri, projections, squery.selection, squery.selectionArgs, squery.orderBy);
```

For another example about block:

```java
(A = 1 and B = 2) OR (C = 3)
```

* 1. Use `brace()`

```java
$.brace(
    $.equal("A", 1).and().equal("B", 2)
).or().equal("C", 3);
```

* 2. Use `begin()` + `end()`

```java
$.begin()
  .equal("A", 1).and().equal("B", 2)
 .end()
 .or().equal("C", 3);
```

* 3. Structured `or` operator

```java
$.or(
    $.equal("A", 1).and().equal("B", 2),
    $.equal("C", 3)
);
```

* 4. Structured `and` operator

```java
$.and(
    $.equal("A", 1),
    $.equal("B", 2)
)
.or()
.equal("C", 3);
```

* 5. Structured `or` + `and` operators

```java
$.or(
    $.and(
        $.equal("A", 1),
        $.equal("B", 2)
    ),
    $.equal("C", 3)
);
```

## Operators

```java
$.in()
```

```java
squery = $.in("Id", Arrays.asList("1", "2", "3"));
System.out.println(squery.toString()); // (Id IN (1,2,3))
```

## Installation

via jcenter

```gradle
repositories {
    jcenter()
}

dependencies {
    compile 'com.infstory:squery:1.0.0'
}
```

Or via jitpack.io

```gradle
repositories {
    maven {
        url "https://jitpack.io"
    }
}

dependencies {
    compile 'com.github.yongjhih:squery:1.0.0'
}
```

## For [ActiveAndroid](https://github.com/pardom/ActiveAndroid)

```java
List<Note> notes = new Select().from(Note.class)
    .where(squery.selection, squery.selectionArgs)
    .execute();
```

## Test

```
$ ./gradlew test
:squery:test

squery.MainTests > testEqual PASSED

squery.MainTests > testCascadedAndOr STANDARD_OUT
    (A = '1') AND (B = '2') OR (C = '3')
    (A = ?) AND (B = ?) OR (C = ?), [1, 2, 3]

squery.MainTests > testCascadedAndOr PASSED

squery.MainTests > testCombineCascadedStructuredAndOr STANDARD_OUT
    (((A = '1') AND (B = '2')) OR ((C = '3')))
    (((A = ?) AND (B = ?)) OR ((C = ?))), [1, 2, 3]
    (((A = '1')) AND ((B = '2'))) OR (C = '3')
    (((A = ?)) AND ((B = ?))) OR (C = ?), [1, 2, 3]
    (((((A = '1')) AND ((B = '2')))) OR ((C = '3')))
    (((((A = ?)) AND ((B = ?)))) OR ((C = ?))), [1, 2, 3]

squery.MainTests > testCombineCascadedStructuredAndOr PASSED

squery.MainTests > testIn PASSED
```

## See Also

ref. https://gist.github.com/yongjhih/e68184ec75d56d9e2804

## License

```
   Copyright 2015 8tory, Inc.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
