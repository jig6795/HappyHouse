<template>
  <div class="content" style="background-color: Azure;">
    <div class="container-fluid">
      <b-container class="bv-example-row" >
        <b-row>
          <b-col></b-col>
          <b-col cols="8">
            <b-card
              class="mt-3"
              header="회원가입"
              style="max-width: 40rem; font-size:50px"
            >
              <b-form>
                <b-form-group>
                  <label for="userEmail" style="font-size:20px; float: left"
                    >Email</label
                  >
                  <b-form-input
                    id="userEmail"
                    v-model="user.userEmail"
                    required
                  ></b-form-input>
                </b-form-group>
                <b-form-group>
                  <label for="userName" style="font-size:20px; float: left"
                    >Name</label
                  >
                  <b-form-input
                    id="userName"
                    v-model="user.userName"
                    required
                  ></b-form-input>
                </b-form-group>
                <b-form-group>
                  <label for="userPassword" style="font-size:20px; float: left"
                    >Password</label
                  >
                  <b-form-input
                    type="password"
                    id="userPassword"
                    v-model="user.userPassword"
                    required
                    @keypress.enter="Join"
                  ></b-form-input>
                </b-form-group>
                <b-button
                  type="button"
                  variant="success"
                  class="m-1"
                  @click="Join"
                  style="float: right"
                  >회원가입</b-button
                >
              </b-form>
              
            </b-card>
          </b-col>
          <b-col></b-col>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

export default {
  name: "Join",
  data: function() {
    return {
      user: {
        userEmail: "",
        userPassword: "",
        userName: ""
      },
      message: ""
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },
  methods: {
    Join: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch("JOIN", this.user)
        .then(() => this.$router.replace(`/${this.nextRoute}`),
        alert('회원가입을 축하합니다. 메인 페이지로 이동합니다'))
        .catch(({ message }) => (this.msg = message));
    }
  }
};
</script>

<style scope>
#login-div {
  text-align: center;
}
</style>
