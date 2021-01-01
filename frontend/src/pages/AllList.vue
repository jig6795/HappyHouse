<template>
  <div class="content" style="background-color: Azure;">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card v-if="getAccessToken">
            <div
              class="jumbotron jumbotron-fluid"
              style="margin: 0 auto; padding: 10px; background-color: #94bbe9;"
            >
              <div class="container-fluid">
                <h1 class="nameColor">All Search</h1>
              </div>
            </div>
            <div class="container-fluid">
              <section id="index_section">
                <div class="row">
                  <div class="card col-sm-12 mt-1" style="min-height: 850px;">
                    <div class="card-body">
                      시도 :
                      <select
                        id="sido"
                        v-model="selectedsido"
                        required
                        @click="SearchMap()"
                      >
                        <option value="0">선택</option>
                        <option
                          v-for="option in getSearchMap"
                          v-bind:value="option.sido_code"
                          >{{ option.sido_name }}</option
                        >
                      </select>
                      구군 :
                      <select
                        id="gugun"
                        v-model="selectedgugun"
                        required
                        @click="SearchMapGugun()"
                      >
                        <option value="0">선택</option>
                        <option
                          v-for="option in getSearchMapGugun"
                          v-bind:value="option.gugun_code"
                          >{{ option.gugun_name }}</option
                        >
                      </select>
                      <b-button
                        type="button"
                        variant="primary"
                        class="m-1"
                        @click="SearchMapTotalDong"
                        style="float: right"
                        >검색</b-button
                      >
                      <table class="table mt-2">
                        <thead>
                          <tr>
                            <th>번호</th>
                            <th>법정동</th>
                            <th>아파트이름</th>
                            <th>지번</th>
                            <th>지역코드</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr
                            v-for="(item, index) in getSearchMapTotalDong"
                            :key="index"
                          >
                            <!-- <td>{{item}}, {{ index }}</td> -->
                            <td>{{ item.no }}</td>
                            <td>{{ item.dong }}</td>
                            <td>{{ item.aptName }}</td>
                            <td>{{ item.jibun }}</td>
                            <td>{{ item.code }}</td>
                          </tr>
                        </tbody>
                      </table>
                      <div id="paginationWrapper"></div>
                    </div>
                  </div>
                </div>
              </section>
            </div>
          </card>

          <card v-else>
            <h1><strong>로그인 후 사용하세요!</strong></h1>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Card from "src/components/Cards/Card.vue";
import VueRouter from "vue-router";
import { mapGetters } from "vuex";
import Vue from "vue";

Vue.use(VueRouter);

export default {
  components: {
    Card
  },
  data: function() {
    return {
      selectedsido: "",
      selectedgugun: ""
    };
  },
  computed: {
    ...mapGetters([
      "getAccessToken",
      "getSearchMap",
      "getSearchMapGugun",
      "getSearchMapTotalDong"
    ])
  },
  methods: {
    SearchMap: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store.dispatch("SearchMap", "sido");
    },
    SearchMapGugun: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store.dispatch("SearchMapGugun", this.selectedsido);
    },
    SearchMapTotalDong: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store.dispatch("SearchMapTotalDong", this.selectedgugun);
    }
  }
};
</script>
<style>
.nameColor {
  font-weight: bold;
  text-align: center;
  text-shadow: 3px 3px yellowgreen;
  color: mediumseagreen;
}
</style>
