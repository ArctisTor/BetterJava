const NavBar = () => {
  return (
    <>
      <nav className="bg-indigo-700 border-b border-indigo-500">
        <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
          <div className="flex h-20 items-center justify-between">
            <div className="flex flex-1 justify-center">
              <span className="text-white text-4xl font-extrabold tracking-wide">
                VTuber Database
              </span>
            </div>
          </div>
        </div>
      </nav>
    </>
  );
};

export default NavBar;
