# Reusable Composables

To improve reusability of a composable:

- Expose both a stateful and a stateless version of the composable. Stateful version is convenient for callers that don't care about the state and stateless lets callers that need to control or hoist the state do so. Stateless is also easier to test.
