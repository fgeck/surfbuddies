<template>
  <div></div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "MultiSelect",
  props: {
    options: {
      type: Array,
      default: () => [],
    },
  }, // emits to get values ?!
});
</script>
