using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(PS02.Startup))]
namespace PS02
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
