import { defineStore } from "pinia";
import { authStore } from "./auth";
import { User } from "@/models/User";

export const userStore = defineStore("user", {
  state: () => {
    return {
      user: null as User | null,
    };
  },
  getters: {
    getUser(state) {
      return state.user;
    },
  },
  actions: {
    async fetchUser() {
      const authenticationStore = authStore();
      try {
        const response = await fetch(
          `/api/users/${authenticationStore.userIdFromToken()}`,
          {
            method: "GET",
            headers: authenticationStore.withBearerToken(),
          }
        );
        if (!response.ok) {
          throw new Error(
            `could not get user. status: ${response.status} message: ${response.body}`
          );
        }
        const userResponse = (await response.json()) as User;
        this.user = userResponse;
      } catch (e) {
        console.log(e);
        return;
      }
    },
    logout() {
      this.user = null;
    },
  },
});
