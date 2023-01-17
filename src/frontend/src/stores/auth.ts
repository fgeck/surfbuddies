import { DecodedJwt } from "@/models/Jwt";
import { LoginRequest, LoginResponse } from "@/models/Login";
import { defineStore } from "pinia";
import router from "@/router";

export const authStore = defineStore("auth", {
  state: () => {
    const token: string | null = localStorage.getItem("token");
    let decodedToken: DecodedJwt | null = null;
    if (token != null) {
      decodedToken = JSON.parse(atob(token.split(".")[1])) as DecodedJwt;
    }
    return {
      token: token,
      decodedToken: decodedToken,
    };
  },
  getters: {
    getToken: (state) => state.token,
    getDecodedToken: (state) => state.decodedToken,
  },
  actions: {
    async login(login: LoginRequest) {
      try {
        const response = await fetch("/api/auth/login", {
          method: "POST",
          body: JSON.stringify(login),
          headers: { "Content-Type": "application/json; charset=UTF-8" },
        });
        if (!response.ok) {
          throw new Error(
            `could not login user. status: ${response.status} message: ${response.body}`
          );
        }
        const loginResponse = (await response.json()) as LoginResponse;
        localStorage.setItem("token", loginResponse.token);
        const decodedToken: DecodedJwt = JSON.parse(
          atob(loginResponse.token.split(".")[1])
        );
        localStorage.setItem("decodedToken", JSON.stringify(decodedToken));
        this.token = loginResponse.token;
        this.decodedToken = decodedToken;
        router.push({ path: "/" });
      } catch (e) {
        console.log(e);
        return;
      }
    },
    logout() {
      this.token = "";
      this.decodedToken = null;
      localStorage.removeItem("token");
      localStorage.removeItem("decodedToken");
      router.push({ path: "/" });
    },
  },
});
