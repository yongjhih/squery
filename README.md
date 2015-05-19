# Squery

The selection and selectionArgs helper, `SQLiteQueryBuilder.appendWhere(where)` whereClause alternative.

To generate sql string of selection and selectionArgs from `String[]`/`List<?>` selectionArgs.

## Usage

For example:

```java
    (A = 1 and B = 2) OR (C = 3)
```

```java
String selection = "(A = ? and B = ?) OR (C = ?)";
selectionArgs[0] = "1";
selectionArgs[1] = "2";
selectionArgs[2] = "3";

getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
```

```java
Squery query = $.or(
        $.and(
            $.equal("A", 1),
            $.like("B", 2)
        ),
        $.notEqual(C, 3)
    );

getContentResolver().query(uri, projection, query.selection, query.selectionArgs, sortOrder);
```

## License

```
                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/

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
