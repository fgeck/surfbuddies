<template>
  <div
    class="text-center py-2 mr-4 mt-2 rounded-md shadow-xl lg:absolute lg:right-0 bg-gray-200"
  >
    <router-link
      to="/"
      class="block px-4 py-2 text-sm rounded-lg hover:bg-blue-100"
      @click="dropdownClicked"
    >
      Home
    </router-link>
    <router-link
      to="/profile"
      class="block px-4 py-2 text-sm rounded-lg hover:bg-blue-100"
      @click="dropdownClicked"
    >
      Profile
    </router-link>
    <button
      type="submit"
      class="block px-4 py-2 text-sm rounded-lg hover:bg-blue-100"
      @click="logoutUser"
    >
      Logout
    </button>
  </div>
</template>

<script lang="ts">
import { authStore } from "@/stores/auth";
import { userStore } from "@/stores/user";
import { defineComponent } from "vue";

export default defineComponent({
  name: "UserMenuDropdown",
  emits: ["dropdownClicked"],
  methods: {
    dropdownClicked() {
      this.$emit("dropdownClicked");
    },
    logoutUser() {
      this.dropdownClicked();
      const authenticationStore = authStore();
      const loggedinUserStore = userStore();
      loggedinUserStore.logout();
      authenticationStore.logout();
    },
  },
});
</script>
