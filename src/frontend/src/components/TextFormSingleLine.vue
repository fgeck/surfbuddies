<template>
  <div class="mt-4">
    <label class="block" for="{{ label }}">{{ label }}</label>
    <input
      :placeholder="label"
      :value="modelValue"
      :type="type"
      @input="$emit('update:modelValue', $event.target?.value)"
      class="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring-1 focus:ring-blue-500"
    />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "TextFormSingleLine",
  props: {
    type: {
      type: String,
      default: "text",
    },
    label: {
      type: String,
      default: "",
    },
    modelValue: {
      type: [String, Number],
      default: "",
    },
  },
});
</script>
