### docker基本概念

## 三大要素
* 镜像 image
镜像就是一个只读的模板,镜像可以创建容器,一个镜像可以创建很多容器
底层是unionFS(联合文件系统),实际由一层层的文件系统组成

* 容器 container
容器是用镜像创建的运行实例.可以创建一个或一组,可以启动,开始,停止,删除.每个容器都是相互隔离,保证安全的平台.


* 仓库 repository
仓库是集中存放镜像文件的场所.
仓库注册服务器(registry)上往往存放着多个仓库,每个仓库存放着多个镜像,每个镜像有不同的标签(tag)



## docker logo
蓝色的大海------------宿主机
鲸鱼-----------------docker
背上的集装箱----------容器实例       from  镜像




## docker容器数据卷
docker容器产生的数据,如果不通过docker commit生成新的镜像,使得数据作为镜像的一部分保存下来,那么当容器删除之后,
数据自然也就没有了.
使用卷保存数据在docker中

卷就是目录或文件,存在于一个或多个容器中,由docker挂载到容器,且不属于联合文件系统,

作用:
```
1. 容器的持久化
2. 容器间继承,共享数据
```

特点:
1. 卷中的更改可以直接生效
2. 数据卷中的修改不会包含在镜像的更新中
3. 数据卷的生命周期一直持续到没有容器使用它为止


## 数据卷容器
一个容器挂载数据卷,其他的容器通过挂载这个容器(父容器)实现数据共享.这个挂载数据卷的容器称为数据卷容器.