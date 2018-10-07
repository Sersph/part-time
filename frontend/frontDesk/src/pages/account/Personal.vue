<template>
  <section class="personal-container">
    <personal-nav/>
    <transition name="opacity" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
import PersonalNav from '@/components/account/PersonalNav';

export default {
  name: 'Persona',
  components: {
    PersonalNav
  }
};
</script>

<style lang="scss">
  .personal-container {
    display: flex;
    & > section:last-child {
      flex: 1;
    }
  }
</style>
