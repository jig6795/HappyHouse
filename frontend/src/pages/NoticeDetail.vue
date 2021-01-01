<template>
  <div class="content" style="background-color: Azure;">
    <div class="container">
      <card>
        <div class="container">
          <h1 class="nameColor">공지사항 상세</h1>
        </div>
        <br />
        <card>
          <div style="text-align:center">
            {{ getBoardItemDetail.dto.content }}
          </div>
        </card>


        <b-button
          type="button"
          variant="contained"
          class="m-1"
          style="float: left"
        >
          <router-link to="notifications">목록으로 돌아가기</router-link>
        </b-button>

        <b-button
          type="button"
          variant="contained"
          class="m-1"
          style="float: right"
          @click="DeleteNotice(getBoardItemDetail.dto.boardId)"
          v-if="getUserManager"
        >삭제하기</b-button>
      </card>
    </div>
  </div>
</template>
<script>
import Card from "src/components/Cards/Card.vue";
import VueRouter from "vue-router";
import { mapGetters } from "vuex";
import Vue from "vue";
import axios from "axios";

export default {
  components: {
    Card
  },
  computed: {
    ...mapGetters(["getBoardItemDetail", "getUserManager"])
  },
  methods: {
    DeleteNotice: function(boardId) {
      // console.log(boardId);
      this.$store
        .dispatch("DeleteNotice", boardId)
        .then(() => this.$router.replace(`Notifications`))
        .catch();
    }
  }
};
</script>
<style lang="scss"></style>
