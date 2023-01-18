<template>
  <p>{{ user?.firstname }}</p>
  <p>{{ user?.lastname }}</p>
  <p>{{ user?.userGroup }}</p>
  <p>{{ user?.sports }}</p>
</template>

<script lang="ts">
import { userStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import { defineComponent } from "vue";

export default defineComponent({
  name: "ProfileView",
  setup() {
    const store = userStore();
    if (!store.user) {
      try {
        store.fetchUser();
      } catch (e) {
        console.log(e);
      }
    }
    const userRef = storeToRefs(store);
    return { user: userRef.user };
  },
});
</script>
