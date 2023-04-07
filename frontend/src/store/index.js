import { createStore } from "vuex";

export default new createStore({
    state: {
        sessionToken: "",
        logs:{
            logArr: []
        },
        user:{
            username: "",
        },
        IsLoggedIn: false,
    },
    getters: {
        getSessionToken(state) {
            if (state.sessionToken === null) return localStorage.getItem("sessionToken")
            return state.sessionToken;
        }
    },
    mutations: {
        addLog (state, payload) {
            state.logs.logArr.unshift(payload)
        },
        addUsername (state, payload) {
            state.user.username = payload
        },
        LogIn (state) {
            state.IsLoggedIn = true
        },
        setSessionToken(state, payload) {
            state.sessionToken = payload;
            if (localStorage.getItem("sessionToken") === null) localStorage.setItem("sessionToken", sessionToken)
        },
    },

});