import router from "../router";
import store from "../store";

export default async function sessionToken () {
    const sessionToken = store.getters.getSessionToken
    if (sessionToken === null) {
        alert("Log in to access your profile!") //TODO: make better
        await router.push("/login")
        throw new Error("Session token cannot be null. Login in again.")
    }

    return sessionToken
}
