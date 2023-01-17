<!-- ToDo: Extract from App into dedicated Navigation Bar with extracted Bars for: Admin / User / Anonymous -->
<template>
  <nav class="m-4" v-if="!token">
    <router-link to="/" class="mr-5">Home</router-link>
    <router-link to="/about" class="mr-5">About</router-link>
    <router-link to="/test">Test</router-link>
    <router-link to="/register" class="float-right">Register</router-link>
    <router-link to="/login" class="mr-5 float-right">Login</router-link>
  </nav>
  <nav class="m-4" v-else>
    <router-link to="/" class="mr-5">Home</router-link>
    <router-link to="/about" class="mr-5">About</router-link>
    <router-link to="/test">Test</router-link>
    <!-- Show user bookings -->
    <!-- Show user profile nav -->
    <div class="float-right">
      <button
        @click="dropdownClicked"
        class="items-center rounded-md focus:outline-none"
      >
        {{ decodedToken!.given_name }}
        <svg
          class="w-5 h-5 inline"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 20 20"
          fill="currentColor"
        >
          <path
            fill-rule="evenodd"
            d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
            clip-rule="evenodd"
          />
        </svg>
      </button>
      <UserMenuDropdown v-show="show" @dropdownClicked="dropdownClicked" />
    </div>
  </nav>

  <div class="bg-gray-200">
    <router-view />
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { authStore } from "./stores/auth";
import { storeToRefs } from "pinia";
import UserMenuDropdown from "./components/UserMenuDropdown.vue";

export default defineComponent({
  name: "MainApp",
  setup() {
    const store = authStore();
    const { token, decodedToken } = storeToRefs(store);

    let show = ref(false);

    return { token, decodedToken, show };
  },
  methods: {
    dropdownClicked() {
      this.show = !this.show;
    },
  },
  components: { UserMenuDropdown },
});
</script>
