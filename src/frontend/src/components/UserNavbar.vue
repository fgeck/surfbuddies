<template>
  <nav class="m-4">
    <router-link to="/" class="mr-5">Home</router-link>
    <div class="float-right">
      <button
        @click="dropdownClicked"
        class="items-center rounded-md focus:outline-none"
      >
        {{ decodedToken.given_name }}
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
      <UserMenuDropdown
        v-show="showDropdown"
        @dropdownClicked="dropdownClicked"
      />
    </div>
  </nav>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import UserMenuDropdown from "@/components/UserMenuDropdown.vue";
import { DecodedJwt } from "@/models/Jwt";

export default defineComponent({
  name: "UserNavbar",
  props: {
    decodedToken: {
      type: [DecodedJwt, Object],
      default: null,
    },
  },
  setup() {
    let showDropdown = ref(false);

    return { showDropdown };
  },
  methods: {
    dropdownClicked() {
      this.showDropdown = !this.showDropdown;
    },
  },
  components: { UserMenuDropdown },
});
</script>
