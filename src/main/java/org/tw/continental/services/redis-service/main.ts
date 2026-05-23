import { createClient, RedisClientType } from "redis";
import { createApp } from "./src/app.ts";

const main = async () => {
  const client: RedisClientType = createClient({
    url: "redis://localhost:6379",
  });

  await client.connect();
  const app = createApp(client);

  Deno.serve({ port: 3000 }, app.fetch);
};

main();
