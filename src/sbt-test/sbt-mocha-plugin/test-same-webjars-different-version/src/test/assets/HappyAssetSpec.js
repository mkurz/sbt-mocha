describe("a test asset", function() {
    it("should be happy", function() {
        console.log("I am happy");
    });
    it("should be able to require a main asset", function() {
        require("./HappyAsset").foo();
    });
    it("should be able to require a main resource", function() {
        require("./HappyPublic").foo();
    });
});