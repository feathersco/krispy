# Krispy

Krispy is a testing utility for AWS AppSync mapping templates. You can write unit tests to validate the correctness of your AWS AppSync 
resolver mapping templates without having to deploy them to AWS. 

Krispy uses the Apache Velocity Template Engine to render your `.vtl` files, and implements the same utility functions that AWS AppSync does. 
Simply set up the context arguments and identity values, and Krispy will evaluate the template.

Krispy also provides utilities to help compare rendered JSON output.

Krispy can be used as an executable JAR file, or be used directly in your Java code.

## Krispy Test Case Files

```yaml
# Example test case file
description: Some description
warnOnAdd: true  # When set to true, strictly additive changes will result in a warning
cognitoIdentityId: "some-test-id"
args:
    someArg: 1
    otherArg: "foo"
templateFile: "path/to/template.vtl"
```

## Limitations

* Can only support rendering one template (no chaining).
    * Considering adding more expressive functionality to test request/response mapping together, as well as pipeline resolvers.
* Not all `$util` utility functions are implemented.
* Not all `$util.dynamodb` utility functions are implemented.
    * Doesn't yet support marshalling to `NumberSet`, `Binary`, `BinarySet`, or `S3Object`.
* Not all `$util.time` utility functions are implemented.
* `$util.rds` utility functions are not yet implemented.
* `$util.http` utility functions are not yet implemented.
* `$util.xml` utility functions are not yet implemented.
* `$util.transform` utility functions are not yet implemented.
* So far only exposes `cognitoIdentityId` to be stubbed in the identity context.
* `JsonDiffer` does not do deep diff of arrays.
* `JsonDiffer` does not work if root of the output is not a map.