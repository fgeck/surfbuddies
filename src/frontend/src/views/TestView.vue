<template>
  <div class="text-center text-xl">
    <h1 class="">Used for testing components</h1>
  </div>
  <UserMenuDropdown />
</template>

<script lang="ts">
import { defineComponent } from "vue";
import UserMenuDropdown from "../components/UserMenuDropdown.vue";

export default defineComponent({
  name: "TestView",
  components: { UserMenuDropdown },
});
</script>
