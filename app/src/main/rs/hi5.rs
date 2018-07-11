#pragma version(1)
#pragma rs java_package_name(com.example.li.tveffect)
uchar4 __attribute__((kernel)) invert(uchar4 in)
{
  uchar4 out = in;
  uchar q = rsRand(215) + 20;

  out.r = q;
  out.g = q;
  out.b = q;
  out.a = 255;
  return out;
}