<template>
  <div class="content" style="background-color: Azure;">
    <div class="container-fluid">
      <div class="row">
        <div class="col-6">
          <card v-if="getAccessToken">
            <div
              class="jumbotron jumbotron-fluid"
              style="margin: 0 auto; padding: 10px; background-color: #94bbe9;"
            >
              <div class="container-fluid">
                <h2 class="nameColor">Search</h2>
                <p style="text-align:center">
                  <strong>원하시는 아파트를 검색해보세요.</strong>
                </p>
                <p style="text-align:center">
                  <strong>지역명으로 상세하게 검색할 수 있어요.</strong>
                </p>
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
                      동명 :
                      <select
                        id="AptName"
                        v-model="selecteddong"
                        required
                        @click="SearchMapDong()"
                      >
                        <option value="0">선택</option>
                        <option
                          v-for="option in getSearchMapDong"
                          v-bind:value="option.dong"
                          >{{ option.dong }}</option
                        >
                      </select>
                      <b-button
                        type="button"
                        variant="primary"
                        class="m-1"
                        @click="SearchMapDongList"
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
                            v-for="(item, index) in getSearchMapDongList"
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
        <div class="col-6">
          <card v-if="getMapstate"
            ><gmap-map
              id="map"
              :center="{
                lat: parseFloat(getSearchMapDongList[0].lat),
                lng: parseFloat(getSearchMapDongList[0].lng)
              }"
              :zoom="15"
              :options="options"
              map-type-id="roadmap"
            >
              <gmap-marker :position="center"> </gmap-marker>
              <gmap-marker
                v-for="(item, index) in getSearchMapDongList"
                :key="index"
                :clickable="true"
                :draggable="true"
                :position="{
                  lat: parseFloat(item.lat),
                  lng: parseFloat(item.lng)
                }"
                @click="
                  openWindow(parseFloat(item.lat), parseFloat(item.lng), item)
                "
              ></gmap-marker>
              <gmap-info-window
                @closeclick="window_open = false"
                :opened="window_open"
                :position="infowindow"
                v-for="(item, index) in getSearchDeal"
                :key="index + 1"
              >
                {{ house.name }}의 최저가는 {{ item.deal }}만원 입니다.
              </gmap-info-window>
            </gmap-map>
          </card>
          <card v-else>
            <gmap-map
              id="map"
              :center="center"
              :zoom="15"
              :options="options"
              map-type-id="roadmap"
            >
              <gmap-marker :position="center"> </gmap-marker>
            </gmap-map>
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
import { API_KEY } from "./Maps/API_KEY";
import * as VueGoogleMaps from "vue2-google-maps";
Vue.use(VueRouter);

Vue.use(VueGoogleMaps, {
  load: {
    key: API_KEY
  }
});
export default {
  components: {
    Card
  },
  computed: {
    ...mapGetters([
      "getAccessToken",
      "getSearchMap",
      "getSearchMapGugun",
      "getSearchMapDong",
      "getSearchMapDongList",
      "getMapstate",
      "getSearchDeal"
    ])
  },
  data() {
    return {
      selectedsido: "",
      selectedgugun: "",
      selecteddong: "",
      center: {
        lat: 37.5013068,
        lng: 127.037465
      },
      options: {
        styles: [
          {
            featureType: "water",
            stylers: [
              { saturation: 43 },
              { lightness: -11 },
              { hue: "#0088ff" }
            ]
          },
          {
            featureType: "road",
            elementType: "geometry.fill",
            stylers: [
              { hue: "#ff0000" },
              { saturation: -100 },
              { lightness: 99 }
            ]
          },
          {
            featureType: "road",
            elementType: "geometry.stroke",
            stylers: [{ color: "#808080" }, { lightness: 54 }]
          },
          {
            featureType: "landscape.man_made",
            elementType: "geometry.fill",
            stylers: [{ color: "#ece2d9" }]
          },
          {
            featureType: "poi.park",
            elementType: "geometry.fill",
            stylers: [{ color: "#ccdca1" }]
          },
          {
            featureType: "road",
            elementType: "labels.text.fill",
            stylers: [{ color: "#767676" }]
          },
          {
            featureType: "road",
            elementType: "labels.text.stroke",
            stylers: [{ color: "#ffffff" }]
          },
          { featureType: "poi", stylers: [{ visibility: "off" }] },
          {
            featureType: "landscape.natural",
            elementType: "geometry.fill",
            stylers: [{ visibility: "on" }, { color: "#b8cb93" }]
          },
          { featureType: "poi.park", stylers: [{ visibility: "on" }] },
          {
            featureType: "poi.sports_complex",
            stylers: [{ visibility: "on" }]
          },
          { featureType: "poi.medical", stylers: [{ visibility: "on" }] },
          {
            featureType: "poi.business",
            stylers: [{ visibility: "simplified" }]
          }
        ]
      },
      info_marker: null,
      infowindow: { lat: "", lng: "" },
      house: {
        name: ""
      },
      window_open: false
    };
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
    SearchMapDong: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store.dispatch("SearchMapDong", this.selectedgugun);
    },
    SearchMapDongList: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store.dispatch("SearchMapDongList", this.selecteddong);
    },
    openWindow(a, b, c) {
      this.$store.dispatch("SearchDeal", c.aptName);

      this.infowindow.lat = a;
      this.infowindow.lng = b;
      this.house.name = c.aptName;
      this.window_open = true;
    }
  }
};
</script>

<style>
#map {
  min-height: calc(100vh - 123px);
}
</style>
