[![8Query](art/8Query.png)](art/8Query.png)

# 8Query

The selection and selectionArgs helper, `SQLiteQueryBuilder.appendWhere(where)` whereClause alternative.

To generate sql string of selection and selectionArgs from `String[]`/`List<?>` selectionArgs.

## Usage

For example:

```java
    (A = 1 and B = 2) OR (C = 3)
```

Before

```java
String selection = "(A = ? and B = ?) OR (C != ?)";
selectionArgs[0] = "1";
selectionArgs[1] = "2";
selectionArgs[2] = "3";

getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
```

After

```java
import squery.Squery.$;

Squery query = $.or(
        $.and(
            $.equal("A", 1),
            $.equal("B", 2)
        ),
        $.notEqual("C", 3)
    );

getContentResolver().query(uri, projection, query.selection, query.selectionArgs, sortOrder);
```

## TODO

Singleton:

```java
$.just(A).equal(1).and().just(B).equal(2).or().just(C).notEqual(3);
```

Cascaded:

```java
$.equal(A, 1).and().equal(B, 2).or().equal(C, 3);
```

```java
$.or( // if block needed
  $.equal(A, 1).and().equal(B, 2),
  $.equal(C, 3)
);
```

For another example:

```java
(A < 1) and ((B > 2) or (C like 3))
```

Pseudo cascaded expression:

```java
lt(A, 1).and(gt(B, 2).or().like(C, 3))
```

Pseudo structured expression:

```java
lt(A, 1).and(or(gt(B, 2), like(C, 3))))
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
