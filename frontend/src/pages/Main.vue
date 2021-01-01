<template>
  <div class="content" style="background-color: Azure;">
    <div class="container-fluid">
      <div
              class="jumbotron jumbotron-fluid"
              style="margin: 0 auto; padding: 10px; background-color: #94bbe9;"
            >
              <div class="container-fluid">
                <h1 class="nameColor" style="margin: 20px">Happy House</h1>
              </div>
            </div>
      <br><br><br>
      <div class="row">
        <div class="container">
          <div class="input-group mb-3">
            <b-form-input
              id="inputSearchWord"
              type="text"
              class="form-control"
              placeholder="Search"
            ></b-form-input>
            <div class="input-group-append">
              <b-button
                id="btnSearchWord"
                class="btn btn-success"
                >Go</b-button
              >
            </div>
          </div>
        </div>
      </div>
      <br><br><br>
      <div class="row">
        <div class="col-xl-3 col-md-6">
          <card>
            <h2 slot="header">
              <strong
                ><i class="nc-icon nc-square-pin"></i> Find in Map
              </strong>
              <router-link to="maps">
                <button class="btn btn-info" style="float: right; margin: 5px">
                  Go!
                </button>
              </router-link>
            </h2>
            <div class="col-xs-12">
              <img src="/img/house1.jpg" alt="map" style="width: 100%" />
            </div>
          </card>
        </div>
        <div class="col-xl-3 col-md-6">
          <card>
            <h2 slot="header">
              <strong
                ><i class="nc-icon nc-notification-70"></i> Notifications
              </strong>
              <router-link to="notifications">
                <button
                  class="btn btn-warning"
                  style="float: right; margin:5px"
                >
                  Go!
                </button>
              </router-link>
            </h2>
            <div class="col-xs-12">
              <img src="/img/house4.jpg" alt="map" style="width: 100%" />
            </div>
          </card>
        </div>

        <div class="col-xl-3 col-md-6">
          <card>
            <h2 slot="header">
              <strong><i class="nc-icon nc-notes"></i> All List </strong>
              <router-link to="all">
                <button
                  class="btn btn-success"
                  style="float: right; margin: 5px"
                >
                  Go!
                </button>
              </router-link>
            </h2>
            <div class="col-xs-12">
              <img src="/img/house2.jpg" alt="map" style="width: 100%" />
            </div>
          </card>
        </div>
        <div class="col-xl-3 col-md-6">
          <card>
            <h2 slot="header">
              <strong><i class="nc-icon nc-zoom-split"></i> Search </strong>
              <router-link to="region">
                <button
                  class="btn btn-danger"
                  style="float: right; margin: 5px"
                >
                  Region
                </button>
              </router-link>
              <router-link to="apart">
                <button
                  class="btn btn-primary"
                  style="float: right; margin: 5px"
                >
                  Apart
                </button>
              </router-link>
            </h2>
            <div class="col-xs-12">
              <img src="/img/house3.jpg" alt="map" style="width: 100%" />
            </div>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Card from "src/components/Cards/Card.vue";

export default {
  components: {
    Card
  }
};
</script>
<style></style>
