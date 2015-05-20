[![8Query](art/8Query.png)](art/8Query.png)

# 8Query

Easy to build selection and selectionArgs for sql.

A `SQLiteQueryBuilder.appendWhere(where)` of whereClause alternative.

## Usage

For example:

```java
    (A = 1 and B = 2) OR (C = 3)
```

Before

```java
String selection = "(A = ? and B = ?) OR (C = ?)";
selectionArgs[0] = "1";
selectionArgs[1] = "2";
selectionArgs[2] = "3";

getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
```

After

```java
import squery.Squery.$;

Squery squery = $.or(
        $.and(
            $.equal("A", 1),
            $.equal("B", 2)
        ),
        $.equal("C", 3)
    );

getContentResolver().query(uri, projection, squery.selection, squery.selectionArgs, sortOrder);
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

## TODO

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

vs. pseudo structured expression:

```java
lt(A, 1).and(or(gt(B, 2), like(C, 3))))
```

begin(), end():

```java
lt(A, 1).and().begin()
  .gt(B, 2).or().like(C, 3)
.end()
```

Singleton:

```java
$.just(A).equal().just(1).and().just(B).equal().just(2).or().just(C).equal().just(3);
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
