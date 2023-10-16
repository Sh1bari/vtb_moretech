import axios from "axios";
import { serverURL } from "./config";

const baseUrl = serverURL;

const api = axios.create({
	baseURL: baseUrl,
});

api.interceptors.request.use((req) => {
	return {
		...req,
		baseURL: baseUrl,
	};
});

export { api };