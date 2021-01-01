<template>
  <div class="content" style="background-color: Azure;">
    <div class="container-fluid">
      <b-container class="bv-example-row" v-if="getAccessToken">
        <div class="container">
          <h2 style="margin-top: 20px;">
            회원 탈퇴를 위해 {{ getUserName }}님의 회원정보를 입력해주세요!
          </h2>
          <b-form novalidate>
            <b-form-group>
              <label for="userEmail">Email:</label>
              <b-form-input
                id="userEmail"
                v-model="user.userEmail"
                placeholder="Enter Email"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group>
              <label for="userPassword">Password:</label>
              <b-form-input
                type="password"
                id="userPassword"
                v-model="user.userPassword"
                placeholder="Enter Password"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group>
              <label for="userPassword2">Password Confirm:</label>
              <b-form-input
                type="password"
                id="userPassword2"
                placeholder="Enter Password2"
                required
                @keypress.enter="UserDelete"
              ></b-form-input>
            </b-form-group>
          </b-form>
          <b-button
            type="button"
            variant="primary"
            class="m-1"
            @click="UserDelete"
            style="float: right"
            >탈퇴하기</b-button
          >
        </div>
      </b-container>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import VueRouter from "vue-router";
import { mapGetters } from "vuex";

Vue.use(VueRouter);

export default {
  name: "UserDelete",
  data: function() {
    return {
      user: {
        userEmail: "",
        userPassword: ""
      },
      message: ""
    };
  },
  computed: {
    ...mapGetters(["getAccessToken", "getUserEmail", "getUserName"]),
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },
  methods: {
    UserDelete: function() {
      console.log(this.user);
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch("UserDelete", this.user)
        .then(
          () => this.$router.replace(`/${this.nextRoute}`),
          alert("회원정보 수정이 완료되었습니다. 메인 페이지로 이동합니다")
        )
        .catch(({ message }) => (this.msg = message), alert("실패"));
    }
  }
};
</script>

<style scope>
#login-div {
  text-align: center;
}
</style>
