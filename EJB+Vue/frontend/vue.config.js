module.exports = {
    devServer: {
        proxy: {
            "/api/": {
                target: "http://localhost:8080/web_lab4/"
            }
        }
    }
};