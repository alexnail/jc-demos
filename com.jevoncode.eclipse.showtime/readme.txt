步骤如下:
1。点击 File-<NewProject。选择Plug-in Project，点击Next。新建一个名为com.jevoncode.eclipse.showtime(这将会变成jar的名字)的项目,所有参数采用默认值.
2。在src下新建一个类: ShowTime
3.添加plugin.xml文件
4.修改MANIFEST.MF
5。点击整个项目，右键点击Run as -< Eclipse Application. 此时会运行一个eclipse, 启动之后就能显示启动所需时间.
6.右键Export -< Deployable plug-ins and fragments. 在Directory中输入需要导出的路径, 点击finish后会在该目录下产生一个plugins的目录, 里面就是插件包.把这个包复制到eclipse目录下的plugin目录下. 然后再启动eclipse 便可以看到eclipse启动所花的时间.