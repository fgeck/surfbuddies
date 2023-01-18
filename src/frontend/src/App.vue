<template>
  <div v-if="!token">
    <AnonymousNavbar />
  </div>
  <div v-else>
    <UserNavbar :decodedToken="decodedToken" />
  </div>

  <div class="bg-gray-200">
    <router-view />
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { authStore } from "./stores/auth";
import { storeToRefs } from "pinia";
import UserNavbar from "@/components/UserNavbar.vue";
import AnonymousNavbar from "@/components/AnonymousNavbar.vue";

export default defineComponent({
  name: "MainApp",
  setup() {
    const store = authStore();
    const { token, decodedToken } = storeToRefs(store);

    let showDropdown = ref(false);

    return { token, decodedToken, showDropdown };
  },
  methods: {
    dropdownClicked() {
      this.showDropdown = !this.showDropdown;
    },
  },
  components: { UserNavbar, AnonymousNavbar },
});
</script>
