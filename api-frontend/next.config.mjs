/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
    return [
      {
        source: "/api/:path*",
        destination: "http://servicios.usig.buenosaires.gob.ar/:path*", // Proxy to Backend
      },
    ];
  },
};

export default nextConfig;
