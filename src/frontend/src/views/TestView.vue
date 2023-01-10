<template>
  <div class="text-center text-xl">
    <h1 class="">Used for testing components</h1>
  </div>
  <MultiSelect />
</template>

<script lang="ts">
import { defineComponent } from "vue";
import MultiSelect from "../components/MultiSelect.vue";

export default defineComponent({
  name: "TestView",
  components: { MultiSelect },
});
</script>
