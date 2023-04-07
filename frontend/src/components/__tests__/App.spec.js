import {getByText, render, screen} from "@testing-library/vue";
import {describe, it, expect} from "vitest"
import store from "@/store";
import router from "@/router";
import App from "@/App.vue";



describe("App", ()=>{



    it("App renders", async()=>{
        const wrapper = render(App,{
            global: {
                plugins: [store, router],
            },
        })

    })

})