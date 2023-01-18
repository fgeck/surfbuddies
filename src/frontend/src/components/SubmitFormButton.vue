<template>
  <div class="flex">
    <button
      type="button"
      @click="buttonClicked"
      class="w-full px-6 py-2 mt-4 text-white bg-blue-500 rounded-lg hover:bg-blue-900"
    >
      {{ name }}
    </button>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "SubmitFormButton",
  props: {
    name: {
      type: String,
      default: "",
    },
  },
  emits: ["buttonClicked"],
  methods: {
    buttonClicked() {
      this.$emit("buttonClicked");
    },
  },
});
</script>
